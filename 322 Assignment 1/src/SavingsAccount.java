
public class SavingsAccount extends BaseAccount
{

	/***
	 * Basic Constructor, uses a single call to the overloaded super constructor with the given param
	 * @param name
	 */
	public SavingsAccount(String name)
	{
		super(name);
	}
	
	/***
	 * Adds interest to the Object
	 * @param rate
	 * @return True if successful, false otherwise
	 */
	public boolean addInterest(double rate)
	{
		if (rate > 0)
		{
			this.balance = this.balance*rate;
			System.out.println("Interest applied, Balance is now: " + this.balance);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/***
	 * Adds funds into the account if they're non-negative
	 * @param amount
	 * @return True if successful, false otherwise
	 */
	@Override
	public boolean deposit(double amount)
	{
		if (amount > 0)
		{
			this.balance = this.balance + amount;
			//System.out.println("Your balance is now: " + this.balance);
			return true;
		}
		else
		{
			System.out.println("The amount must be more than 0.");
			return false;
		}
	}
	/***
	 * Withdraws funds if non-negative and there is sufficient funds in the account.
	 * @param amount is the amount to withdraw
	 * @return true if the withdrawal succeeded, false otherwise.
	 */
	public boolean withdraw(double amount)
	{
		if (amount > 0)
		{
			if (this.balance >= amount)
			{
				this.balance = this.balance - amount;
				System.out.println("Your account Balance is now: " + this.balance);
				return true;
			}
			else
			{
				System.out.println("You do not have enough funds to withdraw that amount. Your current balance is: " + this.balance);
				return false;
			}
		}
		else
		{
			System.out.println("Amount must be positive and more than 0.");
			return false;
		}
	}
}
