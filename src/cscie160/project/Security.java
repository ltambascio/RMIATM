package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Security remote interface.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public interface Security extends Remote
{
	public void authenticate (AccountInfo acctInfo) 
			throws RemoteException, SecurityException;
	public void authorize (AccountInfo acctInfo, TransactionType action)
			throws RemoteException, SecurityException;
}
