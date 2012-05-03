package cscie160.project;

import java.rmi.Naming;

/**
 * Server class that creates and binds ATMFactory instance
 * 
 * @author	Larry Tambascio
 * @version	1.1
 */
public class Server
{

	/**
	 * Server class that instantiates an ATMFactory instance.
	 * 
	 * @param args No command line parameters required.
	 */
	public static void main(String[] args)
	{
		try {
			ATMFactory obj = new ATMFactoryImpl();
			Naming.rebind("//localhost/atmfactory", obj);
			System.out.println("ATMFactory bound in registry");
		}
		catch (Exception e) 
		{
			System.out.println("ATMFactory err: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
