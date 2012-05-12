package cscie160.project;


/**
 * Account class for the ATM homework assignment
 * 
 * @author Larry Tambascio
 * @version 1.1
 */
public class Account
{
	private float	balance;
	
	/**
	 * Constructor that sets up the account object with an initial balance.
	 * 
	 * @param	initAmt		Initial balance for the account
	 */
	public Account(float initAmt)
	{
		balance = initAmt;
	}
	
	/**
	 * Deposit money into the account.
	 * 
	 * @param	amount	Amount of money to deposit
	 */
	public void deposit(float amount)
	{
		balance += amount;
	}
	
	/**
	 * Withdraw money from this account.
	 * 
	 * @param	amount	Amount of money to withdraw
	 * @throws	InsufficientFundsException	Thrown if there's not enough money
	 * 										for the withdrawal amount.
	 */
	public void withdraw(float amount) throws InsufficientFundsException
	{
		if (balance >= amount)
			balance -= amount;
		else
			throw new InsufficientFundsException("Insufficient funds in this " +
					"account ($" + balance + ") for this withdrawal");
	}

	/**
	 * Gives the current balance of this account object.
	 * 
	 * @return the balance
	 */
	public float getBalance()
	{
		return balance;
	}

	/**
	 * Shows the balance for this account object.
	 * 
	 * @return A string description with the balance
	 */
	@Override
	public String toString()
	{
		return "Balance: " + balance;
	}
}
