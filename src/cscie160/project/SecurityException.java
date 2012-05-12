package cscie160.project;

/**
 * Security exception to indicate there was either an authentication or
 * authorization problem.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class SecurityException extends Exception
{
	private String	msg;
	
	/**
	 * Constructor that takes a message about what kind of exception it is.
	 * 
	 * @param	msg		Detail about the cause of the exception
	 */
	public SecurityException(String msg)
	{
		this.msg = msg;
	}
	
	/**
	 * Summary of exception
	 */
	@Override
	public String toString()
	{
		return "Security exception: " + msg;
	}
}
