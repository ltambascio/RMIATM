package cscie160.project;

import java.rmi.Naming;

/**
 * Server class that creates and binds Bank and Security instances
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class BankServer
{

	/**
	 * Server class that instantiates the Bank and Security objects.
	 * 
	 * @param args	No command line parameters required.
	 */
	public static void main(String[] args)
	{
		try {
			Security secObj = new SecurityImpl();
			Naming.rebind("//localhost/atmsecurity", secObj);
			System.out.println("Security bound in registry");
			
			Bank obj = new BankImpl();
			Naming.rebind("//localhost/atmbank", obj);
			System.out.println("Bank bound in registry");
		}
		catch (Exception e) 
		{
			System.out.println("BankServer err: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
