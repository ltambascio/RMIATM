package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Security implementation class
 *  
 * @author	Larry Tambascio
 * @version	1.0
 */
public class SecurityImpl extends UnicastRemoteObject implements Security
{
	private HashMap<Integer,Integer>	acctPin;
	
	private HashMap<Integer,AccountAuthorization>	acctAuth;

	/**
	 * No arg constructor for initializing the Security object.
	 * 
	 * @throws RemoteException	Required for being remote
	 */
	public SecurityImpl() throws RemoteException
	{
		// Initialize the account PINs
		acctPin = new HashMap<Integer,Integer>(3);
		acctPin.put(1, 1234);
		acctPin.put(2, 2345);
		acctPin.put(3, 3456);
		
		// Initialize the account authorizations
		acctAuth = new HashMap<Integer,AccountAuthorization>(3);
		acctAuth.put(1, new AccountAuthorization(true, true, true));
		acctAuth.put(2, new AccountAuthorization(true, false, true));
		acctAuth.put(3, new AccountAuthorization(false, true, true));
	}

	/**
	 * Authenticate the passed in account information against what this object
	 * has stored for the correct PIN value.  Throws <code>SecurityException</code>
	 * if the PINs do not match.
	 * 
	 * @param	acctInfo	Account and PIN to authenticate
	 * @throws	RemoteException		Required for RMI
	 * @throws	SecurityException	Indicates authentication has failed.
	 */
	@Override
	public void authenticate(AccountInfo acctInfo)
			throws RemoteException, SecurityException
	{
		System.out.println("SecurityImpl.authenticate - acct #:" + acctInfo.getAcctNumber());
		
		if (acctInfo != null && !acctInfo.getAcctPin().equals(acctPin.get(acctInfo.getAcctNumber())))
			throw new SecurityException("Authentication Failure");
	}

	/**
	 * Validate that the current account information is authorized to have the
	 * specified action taken.  Throw <code>SecurityException</code> if the 
	 * action isn't authorized for the given account.
	 * 
	 * @param	acctInfo	The account the action is intended for
	 * @param	action		The specific type of action requested
	 * @throws	RemoteException		Required for RMI
	 * @throws	SecurityException	Indicates authorization has failed.
	 */
	@Override
	public void authorize(AccountInfo acctInfo, TransactionType action)
		throws RemoteException, SecurityException
	{
		boolean authorized = true;
		
		System.out.println("SecurityImpl.authorize - acct #:" + acctInfo.getAcctNumber());
		
		switch (action)
		{
			case DEPOSIT:
				authorized = acctAuth.get(acctInfo.getAcctNumber()).isDeposit();
				break;
				
			case WITHDRAW:
				authorized = acctAuth.get(acctInfo.getAcctNumber()).isWithdraw();
				break;
				
			case BALANCE:
				authorized = acctAuth.get(acctInfo.getAcctNumber()).isGetBalance();
				break;
		}
		
		if (!authorized)
			throw new SecurityException("Action: " + action + " unauthorized " +
					"for account:" + acctInfo.getAcctNumber());

	}

}
