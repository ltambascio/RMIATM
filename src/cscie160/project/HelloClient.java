package cscie160.project;

import java.rmi.Naming;

/**
 * Client class for the Hello World RMI example.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class HelloClient
{

	/**
	 * @param	args	Command line parameters.
	 */
	public static void main(String[] args)
	{
		try {
		    Hello obj = (Hello)Naming.lookup("//localhost/HelloServer");
		    System.out.println("Server returned : " + obj.sayHello());
		    System.out.println("Type of HelloServer: " + obj.getClass().getName());
		} 
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}

}
