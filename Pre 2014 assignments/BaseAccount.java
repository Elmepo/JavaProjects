/**
 * An abstract class to represent a basic Bank Account

 */
public abstract class BaseAccount
    implements Comparable<BaseAccount>
{
    private static int nextAcctNumber = 1;
    protected String owner;
    protected int acctNumber;
    protected double balance;

    /**
     * Constructor, used only by subclasses, sets owner,
     * generates unique account number and zeroes balance.
     * @param name owner's name
     */
    protected BaseAccount(String name)
    {
        owner = name;
        acctNumber = nextAcctNumber++;
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
    { /* Insert implementation here */ }

    /**
     * Override equals to be consistent with compareTo
     */
    public boolean equals(Object rhs)
    { /* Insert implementation here */ }

    /**
     * @return String representation of the object
     */
    public String toString()
    { /* Insert implementation here */ } 
}
