import java.util.Scanner;

public class ComputeLoan
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter yearly interest rate: ");
		double annualInterest = input.nextDouble();
		
		double monthlyInterest = annualInterest / 1200;
		
		System.out.print("Enter number of years: ");
		int numberOfYears = input.nextInt();
		
		System.out.print("Enter the loan amount: ");
		double loanAmount = input.nextDouble();
		
		double monthlyPayment = loanAmount * monthlyInterest / (1 - 1 / Math.pow(1 + monthlyInterest, numberOfYears * 12));
		double totalPayment = monthlyPayment * numberOfYears * 12;
		
		System.out.println("The monthly payment is " + (int)(monthlyPayment * 100) / 100.0);
		System.out.println("The total payment is " + (int)(totalPayment * 100) / 100.0);
	}
}