package cscie160.project;

import java.io.Serializable;

/**
 * Account information bean with authentication information.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class AccountInfo implements Serializable
{
	private Integer	acctNumber;
	
	private Integer	acctPin;
	
	/**
	 * Constructor that accepts the account number and PIN.
	 * 
	 * @param	acctNumber	Account number
	 * @param	acctPin		PIN for the account
	 */
	public AccountInfo(Integer acctNumber, Integer acctPin)
	{
		this.acctNumber = acctNumber;
		this.acctPin = acctPin;
	}

	/**
	 * @return This account's number
	 */
	public Integer getAcctNumber()
	{
		return acctNumber;
	}

	/**
	 * @return This account's PIN
	 */
	public Integer getAcctPin()
	{
		return acctPin;
	}

	/**
	 * @return	Values of this object
	 */
	@Override
	public String toString()
	{
		return "Account number:" + acctNumber + "; PIN:" + acctPin;
	}
}
