package cscie160.project;

import java.rmi.Remote;

/**
 * Simple Hello World RMI example interface.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public interface Hello extends Remote
{
	public String sayHello() throws java.rmi.RemoteException;
}
