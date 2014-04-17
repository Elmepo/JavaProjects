import java.util.Arrays;
public class TestAccounts
{
    protected static String newline = System.getProperty("line.separator");
    public static void main(String[] args)
    {
        BaseAccount[] list = new BaseAccount[5];

        list[0] = new SavingsAccount("Fred");
        list[1] = new ChequeAccount("Jim");
        list[2] = new ChequeAccount("Sue");
        list[3] = new SavingsAccount("Jim");
        list[4] = new SavingsAccount("Jill");

        int i;

        // In the following loops do NOT use your knowledge
        // of which member of list[] is of which type
        /***
         * Sets the accounts credit limit if possible
         */
        System.out.println(newline + "<--- SETTING CREDIT LIMIT (50.0) WHERE APPLICABLE --->" + newline);
        for (i = 0; i < 5; i++) {
            /* Insert code setting 50.0 credit limits */
            if (list[i] instanceof ChequeAccount)
            {
            	((ChequeAccount) list[i]).setCreditLimit(50.0);
            	System.out.println("Credit Limit is now $50.0");
            }
            else
            {
            	System.out.println("Error: Unknown account type/Account cannot set credit limit.");
            }
            //System.out.println(list[i]);
        }

        /***
         * Adds 20.0 to each account
         */
        System.out.println(newline + "<--- DEPOSITING MONEY (20.0) WHERE APPLICABLE --->" + newline);
        for (i = 0; i < 5; i++) {
            /* Insert code depositing 20.0 in each account */
            list[i].deposit(20.0);
            System.out.println(list[i]);
        }

        /***
         * Withdraws 25.0 where possible
         */
        System.out.println(newline + "<--- WITHDRAWING MONEY (25.0) WHERE APPLICABLE --->" + newline);
        for (i = 0; i < 5; i++) {
            /* Insert code withdrawing 25.0 from each account */
        	if (list[i] instanceof SavingsAccount)
        	{
        		((SavingsAccount) list[i]).withdraw(25.0);
        	}
        	else if (list[i] instanceof ChequeAccount)
        	{
        		((ChequeAccount) list[i]).withdraw(25.0);
        	}
        	else
        	{
        		System.out.println("Error: Unknown account type/Account cannot withdraw.");
        	}
            //System.out.println(list[i]);
        }

        /***
         * Adds interest at %8.0 where possible
         */
        System.out.println(newline + "<--- ADDING INTEREST WHERE APPLICABLE --->" + newline);
        for (i = 0; i < 5; i++) {
            /* Insert code adding 8.0% interest as applicable */
        	if (list[i] instanceof SavingsAccount)
        	{
        		((SavingsAccount) list[i]).addInterest(8.0);
        	}
        	else
        	{
        		System.out.println("Error: Unknown Account type/Account cannot add interest.");
        	}
            //System.out.println(list[i]);
        }

        /***
         * Sorts the list according the name, then acctNumber where both names are the same.
         */
        System.out.println(newline + "<--- NOW SORTING LIST --->" + newline);
    	Arrays.sort(list);
    	for (i = 0; i < 5; i++)
    	{
    		System.out.println(list[i]);
    	}
    }
}