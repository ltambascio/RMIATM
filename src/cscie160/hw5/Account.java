package cscie160.hw5;

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
	 * @return the balance
	 */
	public float getBalance()
	{
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance)
	{
		this.balance = balance;
	}

	/**
	 * @return A string description with the balance
	 */
	@Override
	public String toString()
	{
		return "Balance: " + balance;
	}
}
