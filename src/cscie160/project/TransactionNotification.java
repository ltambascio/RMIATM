package cscie160.project;

import java.io.Serializable;

/**
 * Object used to identify a transaction that is about to take place.  "From" 
 * accounts are used for balance and withdrawal transactions, while "To" 
 * accounts are used for deposit.  Transfer transactions will use both from and
 * to accounts.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class TransactionNotification implements Serializable
{
	private TransactionType	txType;
	
	private int		fromAcct;
	
	private int		toAcct;
	
	private boolean	transferTx;

	/**
	 * @return The type of transaction to be performed
	 */
	public TransactionType getTxType()
	{
		return txType;
	}

	/**
	 * @param txType The type of transaction to be performed
	 */
	public void setTxType(TransactionType txType)
	{
		this.txType = txType;
	}

	/**
	 * @return The fromAcct for the transaction
	 */
	public int getFromAcct()
	{
		return fromAcct;
	}

	/**
	 * @param fromAcct The fromAcct for the transaction
	 */
	public void setFromAcct(int fromAcct)
	{
		this.fromAcct = fromAcct;
	}

	/**
	 * @return The toAcct for the transaction
	 */
	public int getToAcct()
	{
		return toAcct;
	}

	/**
	 * @param toAcct The toAcct for the transaction
	 */
	public void setToAcct(int toAcct)
	{
		this.toAcct = toAcct;
	}
	
	/**
	 * @return Is the current transaction a transfer?
	 */
	public boolean isTransferTx()
	{
		return transferTx;
	}

	/**
	 * @param transferTx The fromAcct for the transaction
	 */
	public void setTransferTx(boolean transferTx)
	{
		this.transferTx = transferTx;
	}

	/**
	 * Summarizes the current transaction.
	 */
	@Override
	public String toString()
	{
		String	retVal = null;
		
		if (transferTx)
			retVal = "Transfer from account " + fromAcct + " to account " +
					toAcct;
		else
		{
			switch (txType)
			{
				case DEPOSIT:
					retVal = "Deposit into account " + toAcct;
					break;
					
				case WITHDRAW:
					retVal = "Withdraw from account " + fromAcct;
					break;
					
				case BALANCE:
					retVal = "Balance request from account " + toAcct;
					break;
			}
		}
		return retVal;
	}
}
