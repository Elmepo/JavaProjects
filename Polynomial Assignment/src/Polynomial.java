import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Polynomial Program
 * 
 * This program is designed to provide a basic framework for a polynomial in code,
 * although it isn't designed to provide anything other than basic mathematical functions and
 * the loading of these polynomials.
 * 
 * @author Scott Gardner
 */

public class Polynomial
{
	
	public String everything = null;
	public LinkedList<long[]> poly = new LinkedList<long[]>();
	public long[] polyComps = new long[2];
	
	public Polynomial()
	{
	}
	
	/**
	 * Function that returns a polynomial containing the monomials in a file, each monomial should be a coefficient
	 * and an exponent separated by a comma, and each monomial to a separate line.
	 * 
	 * @param pFile a file to be read from
	 * @return Returns a polynomial filled with the contents of the file.
	 */
	public Polynomial readFile(File pFile)
	{
		Polynomial resultPoly = new Polynomial();
		try {
		BufferedReader reader = new BufferedReader(new FileReader(pFile));
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			String[] tempComps = line.split(",");
			long[] polynomialComponents = new long[2];
			polynomialComponents[0] = Long.parseLong(tempComps[0]);
			polynomialComponents[1] = Long.parseLong(tempComps[1]);
			resultPoly.InsertTerm(polynomialComponents);
		}
		reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultPoly;
	}
	
	/**
	 * Function that returns a polynomial filled with entered terms. The size of the polynomial is defined as
	 * the number of monomials.
	 * 
	 * @return Returns a polynomial filled with the entered polynomial
	 */
	public Polynomial scannerRead()
	{
		Polynomial resultPoly = new Polynomial();
		Scanner scanIn = new Scanner(System.in);
		System.out.println("Enter the size of the polynomial: ");
		int polysize = Integer.parseInt(scanIn.nextLine());
		for (int i = 0; i < polysize; i++)
		{
			System.out.println("Please enter a coefficient: ");
			String[] holderArr = new String[2];
			String tempString = scanIn.nextLine();
			holderArr[0] = tempString;
			System.out.println("Please enter a power: ");
			tempString = scanIn.nextLine();
			holderArr[1] = tempString;
			long[] polyArr = new long[2];
			polyArr[0] = Long.parseLong(holderArr[0]);
			polyArr[1] = Long.parseLong(holderArr[1]);
			resultPoly.InsertTerm(polyArr);
		}
		scanIn.close();
		return resultPoly;
	}
	
	/**
	 * Function to insert terms to the end of a polynomial.
	 * 
	 * @param terms The Terms to be inserted, which should be a long array filled with only two items, a
	 * coefficient and an exponent
	 * @return Returns 0;
	 */
	public int InsertTerm(long[] terms)
	{
		this.poly.add(terms);
		return 0;
	}
	
	/**
	 * Function to calculate the total value of a polynomial, given a polynomial and a value to be susbstituted.
	 * 
	 * @param polynomial The polynomial to be calculated
	 * @param givenValue The value for which is to be substituted in place of a
	 * @return returns a long of the calculated value
	 */
	public long getTotalValue(Polynomial polynomial, int givenValue)
	{
		int len = polynomial.poly.size();
		long[][] temp = new long[len][2];
		long total = 0;
		long runningtotal = 0;
		for (int i = 0; i < len; i++)
		{
			temp[i] = polynomial.poly.get(i);
			long[] g = polynomial.poly.get(i);
			total = (long) Math.pow(givenValue, g[1]);
			total = total * g[0];
			runningtotal += total;
		}
		return runningtotal;
	}
	
	/**
	 * A polynomial can call this function to add another polynomial to it, and returns a polynomial consisting
	 * of this value.
	 * 
	 * @param polynomial The polynomial to be added to the calling polynomial
	 * @return returns a polynomial correlating to the value of the added polynomials
	 */
	public Polynomial add(Polynomial polynomial)
	{
		int len1 = this.poly.size();
		int len2 = polynomial.poly.size();
		Polynomial resultPoly = new Polynomial();
		
		//Setting up the LinkedLists. These will be used to add into the new polynomial
		LinkedList<long[]> ll1 = new LinkedList<long[]>();
		long[] cheatsArray = {0,0};	//Had difficulties figuring out how to properly go through the
		ll1.add(cheatsArray);		//LinkedList without ending up eventually with a IndexOutOfBounds
		for (int i = 0; i < len1; i++)//So I just hacked this up, basically ensures that there's
		{							//Always an element at 0, albeit one that's never accessed.
			long[] temp = this.poly.get(i);
			ll1.add(temp);
		}
		LinkedList<long[]> ll2 = new LinkedList<long[]>();
		ll2.add(cheatsArray);
		for (int i = 0; i < len2; i++)
		{
			long[] temp = polynomial.poly.get(i);
			ll2.add(temp);
		}
		
		while (ll1.size() > 1 && ll2.size() > 1)
		{
			long[] first = ll1.get(1);
			long[] second = ll2.get(1);
			if (first[1] > second[1])
			{
				resultPoly.InsertTerm(first);
				ll1.remove();
			}
			else if (second[1] > first[1])
			{
				resultPoly.InsertTerm(second);
				ll2.remove();
			} else {
				if (first[1] == second[1])
				{
					if (first[0] + second[0] != 0)
					{
						long[] newMonomial = new long[2];
						newMonomial[0] = first[0] + second[0];
						newMonomial[1] = first[1];
						resultPoly.InsertTerm(newMonomial);
						ll2.remove();
						ll1.remove();
					}
				}
			}
		}
		if (ll1.size() > 1)
		{
			for (int i = 1; i < ll1.size(); i++)
			{
				long[] temp = ll1.get(i);
				resultPoly.InsertTerm(temp);
			}	
		}
		if (ll2.size() > 1)
		{
			for (int i = 1; i < ll2.size(); i++)
			{
				long[] temp = ll2.get(i);
				resultPoly.InsertTerm(temp);
			}
		}
		return resultPoly;
	}
	
	/**
	 * A polynomial can call this function to differentiate itself, and subsequently returns the result as a
	 * polynomial
	 * 
	 * @return Returns a polynomial consisting to the value of the differentiated polynomial
	 */
	public Polynomial differentiate()
	{
		Polynomial derivedPoly = new Polynomial();
		long[] monomial = new long[2];
		for (int i = 0; i < this.poly.size(); i++)
		{
			monomial = this.poly.get(i);
			if (monomial[1] == 0) //I.e a constant
			{
				//Do nothing I decided that this could just be implemented when the monomial is printed.
			} else {
				monomial[0] = monomial[0] * monomial[1];
				monomial[1] = monomial[1] - 1;
				derivedPoly.InsertTerm(monomial);
			}
		}
		return derivedPoly;
	}
	
	/**
	 * Function to multiply a polynomial by another polynomial
	 * 
	 * @param polynomial The polynomial to be multiplied by
	 * @return Returns a polynomial consisting of both polynomials multiplied. It won't simplify it though.
	 */
	public Polynomial multiply(Polynomial polynomial)
	{
		Polynomial newPolynomial = new Polynomial();
		int len1 = this.poly.size();
		int len2 = polynomial.poly.size();
		for (int i = 0; i < len1; i++)
		{
			long[] firstPoly = this.poly.get(i);
			//Polynomial tempPolynomial = new Polynomial();
			for (int j = 0; j < len2; j++)
			{
				long[] secondPoly = polynomial.poly.get(j);
				long[] multiPoly = new long[2];
				multiPoly[0] = firstPoly[0] * secondPoly[0];
				multiPoly[1] = firstPoly[1] + secondPoly[1];
				newPolynomial.InsertTerm(multiPoly);
			}
			//newPolynomial = tempPolynomial.add(newPolynomial);
		}
		/*for (int t = 0; t < newPolynomial.poly.size(); t++)
		{
			long[] temp = newPolynomial.poly.get(t);
			System.out.println(temp[0] + " AND " + temp[1]);
		}*/
		return newPolynomial;
	}
	
}