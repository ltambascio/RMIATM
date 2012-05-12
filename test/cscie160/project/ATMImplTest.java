package cscie160.project;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import cscie160.project.ATMImpl;

/**
 * This test will validate the ATMImpl class that it deposits, withdraws, and 
 * returns the deposit correctly.  It now requires the BankServer process be 
 * running.  It is also rerunnable if each test returns account 1 to a zero
 * balance.
 * 
 * @author	Larry Tambascio
 * @version	1.2
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
	 * @throws	SecurityException	Authentication or authorization issue
	 * @throws InsufficientFundsException If there aren't enough funds for the 
	 * 										withdrawal
	 */
	@Test
	public void testDeposit() throws RemoteException, SecurityException, InsufficientFundsException
	{
		AccountInfo acctInfo = new AccountInfo(new Integer(1), new Integer(1234));
		
		atmImpl.deposit(acctInfo, 375);
		assertEquals("desposited $375", 375, atmImpl.getBalance(acctInfo), 0);
		atmImpl.deposit(acctInfo, 125);
		assertEquals("deposited another $125", 500, atmImpl.getBalance(acctInfo), 0);
		atmImpl.withdraw(acctInfo, 500);
		assertEquals("returning to starting balance", 0, atmImpl.getBalance(acctInfo), 0);
	}

	/**
	 * Test the withdraw and get balance functions.
	 * @throws	RemoteException		Required since it an RMI implementation
	 * @throws	SecurityException	Authentication or authorization issue
	 * @throws	InsufficientFundsException If there isn't enough funds for the 
	 * 										withdrawal
	 */
	@Test
	public void testWithdraw() throws RemoteException, SecurityException, InsufficientFundsException
	{
		AccountInfo acctInfo = new AccountInfo(1, 1234);
		
		atmImpl.deposit(acctInfo, 400);
		assertEquals("first deposit $400", 400, atmImpl.getBalance(acctInfo), 0);
		atmImpl.withdraw(acctInfo, 75);
		assertEquals("now withdraw 75", 325, atmImpl.getBalance(acctInfo), 0);
		atmImpl.withdraw(acctInfo, 225);
		assertEquals("and another 225", 100, atmImpl.getBalance(acctInfo), 0);
		atmImpl.withdraw(acctInfo, 100);
		assertEquals("returning to starting balance", 0, atmImpl.getBalance(acctInfo), 0);
	}

}
