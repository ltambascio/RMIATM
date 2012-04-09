package cscie160.hw5;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Remote implementation class for the simple Hello World RMI application. 
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class HelloImpl extends UnicastRemoteObject implements Hello
{
	private String name;

	public HelloImpl(String s) throws RemoteException
	{
		super();
		name = s;
	}

	@Override
	public String sayHello() throws RemoteException
	{
		return "Hello World!!";
	}

	public static void main(String args[])
	{
		// Create and install a security manager
		// System.setSecurityManager(new RMISecurityManager());
		try {
			HelloImpl obj = new HelloImpl("HelloServer");
			Naming.rebind("//localhost/HelloServer", obj);
			System.out.println("HelloServer bound in registry");
		}
		catch (Exception e) 
		{
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
