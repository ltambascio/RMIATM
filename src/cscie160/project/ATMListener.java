package cscie160.project;

/**
 * ATM Listener interface that allows a client to be notified of a pending 
 * transaction.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public interface ATMListener
{
	/**
	 * Method invoked by ATMImpl altering clients that a transaction is about
	 * to be performed.
	 *  
	 * @param	txNotif		Object summarizing transaction.
	 */
	public void transactionNotify(TransactionNotification txNotif);
}
