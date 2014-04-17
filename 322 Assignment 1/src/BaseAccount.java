import java.util.concurrent.atomic.AtomicInteger;

/**
 * An abstract class to represent a basic Bank Account

 */
public abstract class BaseAccount
    implements Comparable<BaseAccount>
{
    protected String owner;
    protected int acctNumber;
    protected double balance;
    protected String newline = System.getProperty("line.separator");
    static AtomicInteger nextAcctNumber = new AtomicInteger();
    
    //Default constructor for Subclasses
    protected BaseAccount()
    {
    }

    /**
     * Constructor, used only by subclasses, sets owner,
     * generates unique account number and zeroes balance.
     * @param name owner's name
     */
    protected BaseAccount(String name)
    {
        owner = name;
        //prevAcctNumber = prevAcctNumber + 1;
        //acctNumber = (int) Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
        //prevAcctNumber = acctNumber;
        acctNumber = nextAcctNumber.incrementAndGet();
        balance = 0.0;
    }
    
    /**
     * Makes a deposit. Implementations will at least verify
     * the amount is positive before making the deposit.
     * @param amount the amount to deposit
     * @return true if amount is positive otherwise false
     */
    public abstract boolean deposit(double amount);

    /**
     * Implements the Comparable interface, making accounts
     * comparable through alphabetical ordering by owner's name.
     */
    public int compareTo(BaseAccount rhs)
    {
    	String tempString = this.owner;
    	if (tempString.compareTo(rhs.owner) == 0)
    	{
    		this.equals(rhs);
    		return 0;
    	}
    	else if (tempString.compareTo(rhs.owner) < 0)
    	{
    		//Use if rhs is after this
    		//Printlns for Debugging
    		/*System.out.println(this.toString());
    		System.out.println(rhs.toString());
    		System.out.println("Less0");*/
    		return -10;
    	}
    	else
    	{
    		//use if this is after rhs.
    		//Printlns for Debugging
    		/*System.out.println(rhs.toString());
    		System.out.println(this.toString());
    		System.out.println("More0");*/
    		return 10;
    	}
    }

    /**
     * Override equals to be consistent with compareTo
     */
    @Override
    public boolean equals(Object rhs)
    {
    	if (this.toString() == rhs.toString())
    	{
    		//System.out.println("These Accounts are the same");
    		return true;
    	}
    	else
    	{
    		//System.out.println("These Accounts are not the same");
    		return false;
    	}/* Insert implementation here */
    }

    /**
     * @return String representation of the object
     */
    public String toString()
    {
    	/* Insert implementation here */
    	return this.owner + "\t" + this.acctNumber + "\t" + this.balance;
    } 
}
