package cscie160.project;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ATM Factory remote interface.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public interface ATMFactory extends Remote
{
	/**
	 * Returns remote ATM reference.
	 * 
	 * @return	Remote ATM reference
	 * @throws	RemoteException	Required for RMI
	 * @throws	NotBoundException Thrown by lookup
	 * @throws	MalformedURLException Thrown by lookup
	 */
	public ATM getATM() throws RemoteException, MalformedURLException, NotBoundException;
}
