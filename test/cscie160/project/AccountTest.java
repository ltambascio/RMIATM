package cscie160.project;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import cscie160.project.Account;

/**
 * Test the Account class.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class AccountTest
{
	private Account account;

	/**
	 * Initialize the test harness by simply instantiating an Account instance.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		account = new Account(0);
	}

	/**
	 * Test the get balance function.
	 * @throws RemoteException 
	 */
	@Test
	public void testGetBalance() throws RemoteException
	{
		assertEquals("initialized correctly", 0, account.getBalance(), 0);
		account.deposit(2500);
		assertEquals("balance udpated", 2500, account.getBalance(), 0);
	}

	/**
	 * Test the set balance function.
	 * @throws RemoteException 
	 */
	@Test
	public void testSetBalance() throws RemoteException
	{
		account.deposit(10000);
		assertEquals("balance udpated", 10000, account.getBalance(), 0);
	}

}
