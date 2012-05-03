package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ATM remote interface
 * 
 * @author	Larry Tambascio
 * @version	1.1
 */
public interface ATM extends Remote
{
	public void deposit(int acctNum, float amount) throws RemoteException;
	public void withdraw(int acctNum, float amount) throws RemoteException;
	public Float getBalance(int acctNum) throws RemoteException;
}