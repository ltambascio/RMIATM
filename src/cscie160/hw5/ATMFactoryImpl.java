package cscie160.hw5;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Remote implementation class for the ATM factory, it will create and bind an
 * instance of the ATMImpl class.
 * 
 * @author	Larry Tambascio
 * @version 1.0
 */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory
{

	/**
	 * No arg constructor required by UnicastRemoteObject.  This method binds 
	 * an instance of the ATMImpl class to be returned by the getATM method.
	 * 
	 * @throws RemoteException	Required as an RMI object
	 */
	protected ATMFactoryImpl() throws RemoteException
	{
		super();
		try {
			ATM obj = new ATMImpl();
			Naming.rebind("//localhost/atm", obj);
			System.out.println("ATM bound in registry");
		}
		catch (Exception e) 
		{
			System.out.println("ATM err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Returns remote ATM reference.
	 * 
	 * @return	Remote ATM reference
	 * @throws	RemoteException	Required for RMI
	 * @throws	NotBoundException Thrown by lookup
	 * @throws	MalformedURLException Thrown by lookup
	 */
	@Override
	public ATM getATM() throws RemoteException, MalformedURLException, 
			NotBoundException
	{
		ATM atm;
		
		atm = (ATM) Naming.lookup("//localhost/atm");

		return atm;
	}

}
