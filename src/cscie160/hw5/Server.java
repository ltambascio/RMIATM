package cscie160.hw5;

import java.rmi.Naming;

public class Server
{

	/**
	 * @param args
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
