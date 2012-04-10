package cscie160.hw5;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

/**
 * This test will validate the ATMImpl class that it deposits, 
 * withdraws, and returns the deposit correctly.
 * 
 * @author	Larry Tambascio
 * @version	1.1
 */
public class ATMImplTest
{
	private ATMImpl atmImpl;
	
	/**
	 * Create ATM Impl instance to test with.
	 * 
	 * @throws RemoteException
	 */
	@Before
	public void setUp() throws RemoteException
	{
		atmImpl = new ATMImpl();
	}

	/**
	 * Test the deposit and get balance functions.
	 * 
	 * @throws RemoteException Required since it an RMI implementation
	 */
	@Test
	public void testDeposit() throws RemoteException
	{
		atmImpl.deposit(1, 375);
		assertEquals("desposited $375", 375, atmImpl.getBalance(1), 0);
		atmImpl.deposit(1, 125);
		assertEquals("deposited another $125", 500, atmImpl.getBalance(1), 0);
	}

	/**
	 * Test the withdraw and get balance functions.
	 * @throws RemoteException Required since it an RMI implementation
	 */
	@Test
	public void testWithdraw() throws RemoteException
	{
		atmImpl.deposit(2, 600);
		assertEquals("first deposit $600", 700, atmImpl.getBalance(2), 0);
		atmImpl.withdraw(2, 275);
		assertEquals("now withdraw 275", 425, atmImpl.getBalance(2), 0);
		atmImpl.withdraw(2, 225);
		assertEquals("and another 225", 200, atmImpl.getBalance(2), 0);
	}

}
