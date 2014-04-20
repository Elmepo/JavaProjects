import java.io.File;

public class DriverProgram
{
	/**
	 * This is the driver program to test "Polynomial.java"
	 * It tests every function within the program, including it's constructor.
	 * 
	 * Please note that in displaying the polynomials, I didn't make a special case for displaying negatives,
	 * given that x + -y is mathematically the same as x - y
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*----------------------------------------*\
		 * Testing the construction of polynomial *
		 * using both the file read function  and *
		 * the scanner function, using the        *
		 * polynomials provided in the subject    *
		 * outline.                               *
		\*--------------------------------------- */
		Polynomial firstPolynomial = new Polynomial();
		File testFile = new File("src/PolynomialFile.txt");
		firstPolynomial.readFile(testFile);
		System.out.println("First polynomial is comprised of: ");
		for (int i = 0; i < firstPolynomial.poly.size(); i++)
		{
			long[] comps = firstPolynomial.poly.get(i);
			System.out.print(comps[0] + "a^" + comps[1] + " + ");
		}
		
		Polynomial secondPolynomial = new Polynomial();
		secondPolynomial = secondPolynomial.scannerRead();
		System.out.println("Second polynomial is comprised of: ");
		for (int i = 0; i < secondPolynomial.poly.size(); i++)
		{
			long[] comps = secondPolynomial.poly.get(i);
			System.out.print(comps[0] + "a^" + comps[1] + " + ");
		}
		
		/*--------------------------------------------*\
		 * Testing the ability to calculate the value *
		 * of a given polynomial provided a given     *
		 * value, eg: x=2                             *
		\*--------------------------------------------*/
		long calculatedValue = firstPolynomial.getTotalValue(firstPolynomial, 2);
		System.out.println("Calculated value of the first polynomial is: " + calculatedValue);
		
		/*--------------------------------------------*\
		 * Testing the ability to add two polynomials *
		 * together, using a set of rules provided by *
		 * the subject outline.                       *
		\*--------------------------------------------*/
		Polynomial thirdPolynomial = new Polynomial();
		thirdPolynomial = firstPolynomial.add(secondPolynomial);
		System.out.println("The third polynomial is comprised of: ");
		for (int i = 0; i < thirdPolynomial.poly.size(); i++)
		{
			long[] comps = thirdPolynomial.poly.get(i);
			System.out.println(comps[0] + "a^" + comps[1] + " + ");
		}
		
		/*----------------------------------------------*\
		 * Testing the ability to multiply a polynomial *
		 * by another polynomial                        *
		\*----------------------------------------------*/
		Polynomial fourthPolynomial = new Polynomial();
		fourthPolynomial = firstPolynomial.multiply(secondPolynomial);
		System.out.println("The fourth polynomial is comprised of: ");
		for (int i = 0; i < fourthPolynomial.poly.size(); i++)
		{
			long[] comps = thirdPolynomial.poly.get(i);
			System.out.println(comps[0] + "a^" + comps[1] + " + ");
		}
		
		/*----------------------------------------------*\
		 * Testing the ability to obtain the derivative *
		 * of a polynomial.                             *
		\*----------------------------------------------*/
		Polynomial fifthPolynomial = new Polynomial();
		fifthPolynomial = firstPolynomial.differentiate();
		System.out.println("The fifth polynomial is comprised of: ");
		for (int i = 0; i < fifthPolynomial.poly.size(); i++)
		{
			long[] comps = fifthPolynomial.poly.get(i);
			System.out.println(comps[0] + "a^" + comps[1] + " + ");
		}
	}
}
