package cscie160.project;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Remote implementation class for an ATM.
 * 
 * @author	Larry Tambascio
 * @version	1.2
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
	private float cashOnHand;
	
	private Bank bank;
	
	private Security security;
	
	private Vector<ATMListener> clients;

	/**
	 * No arg constructor that defines how much cash on hand the ATM has, gets
	 * a remote reference to the bank, and initializes the vector that will 
	 * hold the client references.
	 * 
	 * @throws	RemoteException		Potentially thrown by parent class
	 */
	protected ATMImpl() throws RemoteException
	{
		super();

		cashOnHand = 500;
		
		clients = new Vector<ATMListener>();

		try
		{
			bank = (Bank) Naming.lookup("//localhost/atmbank");
		}
		catch (MalformedURLException mue)
		{
			mue.printStackTrace();
			throw new RemoteException("Malformed URL", mue);
		}
		catch (NotBoundException nbe)
		{
			nbe.printStackTrace();
			throw new RemoteException("Not bound", nbe);
		}
		
		// initialize security
		try
		{
			security = (Security) Naming.lookup("//localhost/atmsecurity");
		}
		catch (MalformedURLException mue)
		{
			mue.printStackTrace();
			throw new RemoteException("Malformed URL", mue);
		}
		catch (NotBoundException nbe)
		{
			nbe.printStackTrace();
			throw new RemoteException("Not bound", nbe);
		}
	}

	/**
	 * Deposit into the referenced account the amount of money specified.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @param	amount	Amount to deposit into the account.
	 * @throws	RemoteException	Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	@Override
	public void deposit(AccountInfo acctInfo, float amount)
			throws RemoteException, SecurityException
	{
		TransactionNotification txNotif;
		
		txNotif = new TransactionNotification();
		txNotif.setTxType(TransactionType.DEPOSIT);
		txNotif.setToAcct(acctInfo.getAcctNumber());
		notifyListeners(txNotif);
		
		security.authenticate(acctInfo);
		security.authorize(acctInfo, TransactionType.DEPOSIT);
		
		bank.deposit(acctInfo, amount);
		
		System.out.println("Deposit $" + amount + " into account " + acctInfo.getAcctNumber());
	}

	/**
	 * Withdraw from the referenced account the amount of money specified.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @param	amount		Amount to withdraw from the account.
	 * @throws	RemoteException		Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 * @throws	InsufficientFundsException	Thrown if there's not enough money 
	 * 										in the account or the ATM.
	 */
	@Override
	public void withdraw(AccountInfo acctInfo, float amount)
			throws RemoteException, SecurityException, InsufficientFundsException
	{
		TransactionNotification txNotif;
		
		txNotif = new TransactionNotification();
		txNotif.setTxType(TransactionType.WITHDRAW);
		txNotif.setFromAcct(acctInfo.getAcctNumber());
		notifyListeners(txNotif);
				
		security.authenticate(acctInfo);
		security.authorize(acctInfo, TransactionType.WITHDRAW);
		
		if (amount <= cashOnHand)
		{
			bank.withdraw(acctInfo, amount);
			cashOnHand -= amount;
		}
		else
			throw new InsufficientFundsException("ATM cannot dispense " + amount +
					" of money due to insufficient cash at this ATM ($" +
					cashOnHand + ").  We apologize for this inconvenience");
		
		System.out.println("Withdrew $" + amount + " from account " + acctInfo.getAcctNumber());
	}

	/**
	 * Returns the current amount in the referenced account.
	 * 
	 * @param	acctInfo	Account number to operate on.
	 * @return	Account balance for specified account.
	 * @throws	RemoteException		Required since it is an RMI implementation class.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	@Override
	public Float getBalance(AccountInfo acctInfo)
			throws RemoteException, SecurityException
	{
		TransactionNotification txNotif;
		
		txNotif = new TransactionNotification();
		txNotif.setTxType(TransactionType.BALANCE);
		txNotif.setToAcct(acctInfo.getAcctNumber());
		notifyListeners(txNotif);
		
		security.authenticate(acctInfo);
		security.authorize(acctInfo, TransactionType.BALANCE);
		
		System.out.println("ATMImpl.getBalance - acct #:" + acctInfo.getAcctNumber());
		return bank.getBalance(acctInfo);
	}

	/**
	 * Transfer the amount from one account to another account.
	 * 
	 * @param	acctFromInfo	Account money is being transferred from.
	 * @param	acctToInfo		Account money is being transferred to.
	 * @param	amount			Amount to transfer.
	 * @throws	RemoteException		Required for remote calls.
	 * @throws	SecurityException	Authentication or authorization issue
	 */
	@Override
	public void transfer(AccountInfo acctFromInfo, AccountInfo acctToInfo,
			float amount) throws RemoteException, SecurityException, InsufficientFundsException
	{
		TransactionNotification txNotif;
		
		txNotif = new TransactionNotification();
		txNotif.setTransferTx(true);
		txNotif.setFromAcct(acctFromInfo.getAcctNumber());
		txNotif.setToAcct(acctToInfo.getAcctNumber());
		notifyListeners(txNotif);
		
		security.authenticate(acctFromInfo);
		security.authorize(acctFromInfo, TransactionType.WITHDRAW);
		security.authenticate(acctToInfo);
		security.authorize(acctToInfo, TransactionType.DEPOSIT);
		
		
		if (bank.getBalance(acctFromInfo) >= amount)
		{
			bank.withdraw(acctFromInfo, amount);
			bank.deposit(acctToInfo, amount);
			
			System.out.println("Transfer $" + amount + " from account " +
					acctFromInfo.getAcctNumber() + " to account " + 
					acctToInfo.getAcctNumber());
		}
		else
			throw new InsufficientFundsException("Insufficient funds in this " +
					"account ($" + bank.getBalance(acctFromInfo) + ") for this withdrawal");
	}

	/**
	 * Register client as a listener to notifications.
	 * 
	 * @param	atmListener		Client to register
	 */
	@Override
	public void registerListener(ATMListener atmListener)
			throws RemoteException
	{
		clients.add(atmListener);
	}
	
	/**
	 * Notify listeners of a transaction.
	 * 
	 * @param txNotif	Transaction event object
	 */
	public void notifyListeners(TransactionNotification txNotif)
	{
		for (ATMListener listener : clients)
		{
			listener.transactionNotify(txNotif);
		}
	}

}
