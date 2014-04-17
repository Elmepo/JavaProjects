public class ChequeAccount extends BaseAccount
{
	@SuppressWarnings("unused")
	private double creditLimit;
	
	/***
	 * Constructor, calls the super and then sets the credit limit
	 * @param name
	 */
	ChequeAccount(String name)
	{
		super(name);
		this.creditLimit = 0.0;
	}
	
	/***
	 * Adds funds to the account, provided it's non-negative and the account can handle the 0.30
	 * Transfer cost
	 * @param amount
	 * @return True if successful, false otherwise
	 */
	@Override
	public boolean deposit(double amount)
	{
		if (amount < 0)
		{
			System.out.println("Amount must not be negative.");
			return false;
		}
		else
		{
			double finalBalance = this.balance + amount - 0.30;
			if (finalBalance < 0)
			{
				System.out.println("All transactions incur a 0.30 transaction fee. Your account does not have enough funds to cover this transaction. Your current balance is: " + this.balance);
				return false;
			}
			else
			{
				this.balance = finalBalance;
				return true;
			}
		}
	}
	
	/***
	 * Withdraws funds from the account, provided the account has enough funds, the amount is
	 * non-negative, and the account can handle the 0.30 transaction cost
	 * @param amount
	 * @return true if successful, false otherwise
	 */
	public boolean withdraw(double amount)
	{
		if (amount < 0)
		{
			System.out.println("Amount must not be a negative.");
			return false;
		}
		else
		{
			double finalBalance = this.balance - amount - 0.30;
			if (finalBalance < 0)
			{
				System.out.println("All transactions incur a 0.30 transaction fee. Your account does not have enough funds to cover this transaction. Your current balance is: " + this.balance);
				return false;
			}
			else
			{
				this.balance = finalBalance;
				return true;
			}
		}
	}
	
	/***
	 * Sets the objects credit limit, provided it's non-negative
	 * @param limit
	 * @return True if successful, false otherwise
	 */
	public boolean setCreditLimit(double limit)
	{
		if (limit < 0)
		{
			System.out.println("Credit Limit cannot be negative.");
			return false;
		}
		else
		{
			this.creditLimit = limit;
			return true;
		}
	}
}