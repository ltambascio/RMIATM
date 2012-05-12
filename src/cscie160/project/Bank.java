package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface that gives access to accounts
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public interface Bank extends Remote
{
	public void deposit(AccountInfo acctInfo, float amount) throws RemoteException;
	public void withdraw(AccountInfo acctInfo, float amount) throws RemoteException, InsufficientFundsException;
	public float getBalance(AccountInfo acctInfo) throws RemoteException;
}
