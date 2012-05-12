package cscie160.project;

/**
 * Exception to indicate that a withdrawal cannot be made because either the
 * account or the ATM has insufficient funds to complete the withdrawal.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class InsufficientFundsException extends Exception
{
	private String	msg;
	
	/**
	 * Constructor that takes a message about what kind of exception it is.
	 * 
	 * @param	msg		Detail about the cause of the exception
	 */
	public InsufficientFundsException(String msg)
	{
		this.msg = msg;
	}
	
	/**
	 * Summary of exception
	 */
	@Override
	public String toString()
	{
		return "Insufficient funds exception: " + msg;
	}
}
