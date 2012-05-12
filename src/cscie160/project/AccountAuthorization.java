package cscie160.project;

/**
 * Object that will hold the authorization properties for a particular account.
 *  
 * @author	Larry Tambascio
 * @version	1.0
 */
public class AccountAuthorization
{
	private boolean deposit;
	
	private boolean withdraw;
	
	private boolean getBalance;

	/**
	 * Constructor that accepts booleans for the three actions that could be
	 * taken on an account.
	 * 
	 * @param deposit		Are deposits allowed
	 * @param withdraw		Are withdrawals allowed
	 * @param getBalance	Are balance requests allowed
	 */
	public AccountAuthorization(boolean deposit, boolean withdraw,
			boolean getBalance)
	{
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.getBalance = getBalance;
	}

	/**
	 * @return Flag indicating whether deposits are allowed on this account
	 */
	public boolean isDeposit()
	{
		return deposit;
	}

	/**
	 * @return Flag indicating whether withdrawals are allowed on this account
	 */
	public boolean isWithdraw()
	{
		return withdraw;
	}

	/**
	 * @return Flag indicating whether the balance on this account can be retrieved.
	 */
	public boolean isGetBalance()
	{
		return getBalance;
	}

}
