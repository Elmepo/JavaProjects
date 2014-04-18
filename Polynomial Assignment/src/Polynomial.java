import java.io.Console;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Polynomial
{
	//Two Constructors for Polynomial, One for file and one for user input
	//Polynomial.add(), to add two polynomials
	//	Follows 4 rules:
	//		1. Powers are equal, they're added algebraically
	//		2. Powers are unqual, the term with the higher power is inserted in the new polynomial
	//		3. If the exponent is 0, it represents 1, the value of the term is therefore the value of
	//			the coefficient
	//		4. If the result of adding the coefficients results in 0, the term is dropped.
	//Get the value of a polynomial given an input (e.g. x=2)
	//Implement the product of a polynomial by another polynomial, which is the algebraic sum of the
	//	products of one polynomial by all the monomials of another polynomial
	//Implement the derivative operation for a polynomial. E.g. ax^k derived is kax^k-1,
	//	and the derivative of a constant is 0, and the derivative of a polynomial is equal to the sum
	//	of the derivatives of it's polynomial
	//Implement an InsertTerm(), this method should put the input term into a polynomial
	//Test with data provided in assignment text to test all the functionalities that you have defined.
	//Write a driver program
	
	public String everything = null;
	public LinkedList<long[]> poly = new LinkedList<long[]>();
	public long[] polyComps = new long[2];
	
	public Polynomial()
	{
	}
	
	//IMPLEMENT LATER
	//FUCK THIS SHIT
	/*public Polynomial(File pFile) throws IOException
	{
		//Reads the file into a <String>List, then makes this.everything an exact copy.
		List<String> lines = Files.readAllLines(Paths.get(pFile.getAbsolutePath()), Charset.forName("ISO-8859-1"));
		this.everything = lines.toString();
		//Searching for, any text in between [], then making "s" a copy of said text
		Pattern sBPattern = Pattern.compile("\\[(.*?)\\]");
		Matcher sBMatcher = sBPattern.matcher(this.everything);
		String s = null;
		while (sBMatcher.find()) {
			s = sBMatcher.group(1);
		}
		System.out.println(s);
		//Searching for any text within (), then making "s" an exact copy of said text
		Pattern rBPattern = Pattern.compile("\\((.*?)\\)");
		Matcher rBMatcher = rBPattern.matcher(s);
		//Grabs the data from the pair of parentheses and adds it to a polynomial component array
		//Then adds *that* polynomial component array to a linked list
		while (rBMatcher.find()) {
			s = rBMatcher.group(1);
			System.out.println("G1 = " + rBMatcher.group(1));
			String[] tempArr = s.split(",");
			System.out.println("tempArr[0] " + tempArr[0]);
			System.out.println("tempArr[1] " + tempArr[1]);
			polyComps[0] = Integer.parseInt(tempArr[0]);
			polyComps[1] = Integer.parseInt(tempArr[1]);
			System.out.println("polyComps[0]: " + polyComps[0]);
			this.poly.add(polyComps);
		}
		int len = this.poly.size();
		for (int i = 0; i < len; i++)
		{
			//System.out.println(i);
			long[] temp = this.poly.get(i);
			System.out.println("LINKED LISTA: " + temp[0] + " AND " + temp[1]);
		}
		long[] a = this.poly.get(0);
		long[] b = this.poly.get(1);
		long[] c = this.poly.get(2);
		System.out.println(a[0] + " a " + a[1]);
		System.out.println(b[0] + " b " + b[1]);
		System.out.println(c[0] + " c " + c[1]);
		//System.out.println(s);
		this.everything = s;
	}*/
	
	/*public Polynomial(ArrayList pList)
	{
	//IMPLEMENT AT END. 
	}*/
	
	public int InsertTerm(long[] terms)
	{
		this.poly.add(terms);
		return 0;
	}
	
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
			//System.out.println("XXXXX: " + g[0] + "XXXXX" + g[1]);
			total = (long) Math.pow(givenValue, g[1]);
			//System.out.println(total);
			total = total * g[0];
			//System.out.println("TEMPTOTAL: " + total);
			runningtotal += total;
		}
		//System.out.println("RUNNING TOTAL: " + runningtotal);
		return 0;
	}
	
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
			//System.out.println(temp[0]);
		}
		LinkedList<long[]> ll2 = new LinkedList<long[]>();
		ll2.add(cheatsArray);
		for (int i = 0; i < len2; i++)
		{
			long[] temp = polynomial.poly.get(i);
			ll2.add(temp);
			//System.out.println(temp[0]);
		}
		//cheatsArray = ll2.get(1);
		//System.out.println(cheatsArray[0] + ":::::" + cheatsArray[1]);
		
		while (ll1.size() > 1 && ll2.size() > 1)
		{
			long[] first = ll1.get(1);
			long[] second = ll2.get(1);
			//System.out.println(first[0]);
			//System.out.println(second[0]);
			if (first[1] > second[1])
			{
				resultPoly.InsertTerm(first);
				//System.out.println("First EXP Bigger: " + first[1] + " VS " + second[1]);
				ll1.remove();
			}
			else if (second[1] > first[1])
			{
				resultPoly.InsertTerm(second);
				//System.out.println("Second EXP Bigger: " + first[1] + " VS " + second[1]);
				ll2.remove();
			} else {
				if (first[1] == second[1])
				{
					//System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
					if (first[0] + second[0] != 0)
					{
						//System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
						long[] newMonomial = new long[2];
						newMonomial[0] = first[0] + second[0];
						newMonomial[1] = first[1];
						resultPoly.InsertTerm(newMonomial);
						//System.out.println("EXP's were Equal: " + first[0] + " VS " + second[0]);
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
	
	public Polynomial differentiate(/*Polynomial polynomial*/)
	{
		//Differentiation is surprisingly easy. ax^k becomes kax^k-1, constant becomes 0. Just apply
		//this logic to every monomial in the polynomial.
		Polynomial derivedPoly = new Polynomial();
		long[] monomial = new long[2];
		for (int i = 0; i < this.poly.size(); i++)
		{
			monomial = this.poly.get(i);
			if (monomial[1] == 0) //I.e a constant
			{
				//Do nothing for the moment, just a placeholder until I'm awake enough to do it
			} else {
				monomial[0] = monomial[0] * monomial[1];
				monomial[1] = monomial[1] - 1;
				derivedPoly.InsertTerm(monomial);
			}
		}
		return derivedPoly;
	}
	
	public Polynomial multiply(Polynomial polynomial)
	{
		Polynomial newPolynomial = new Polynomial();
		int len1 = this.poly.size();
		int len2 = polynomial.poly.size();
		//long[] firstPoly = new long[2];
		//long[] secondPoly = new long[2];
		//long[] multiPoly = new long[2];
		for (int i = 0; i < len1; i++)
		{
			long[] firstPoly = this.poly.get(i);
			Polynomial tempPolynomial = new Polynomial();
			for (int j = 0; j < len2; j++)
			{
				long[] secondPoly = polynomial.poly.get(j);
				long[] multiPoly = new long[2];
				multiPoly[0] = firstPoly[0] * secondPoly[0];
				multiPoly[1] = firstPoly[1] + secondPoly[1];
				tempPolynomial.InsertTerm(multiPoly);
				//for (int k = 0; k < tempPolynomial.poly.size(); k++)
				//{
				//	long[] arr = tempPolynomial.poly.get(k);
				//	System.out.println("tempPolynomial is: " + arr[0] + " AND " + arr[1]);
				//}
			}
			newPolynomial = tempPolynomial.add(newPolynomial);
		}
		for (int t = 0; t < newPolynomial.poly.size(); t++)
		{
			long[] temp = newPolynomial.poly.get(t);
			//System.out.println(newPolynomial.poly.size());
			System.out.println(temp[0] + " AND " + temp[1]);
		}
		/*long[] t1 = newPolynomial.poly.get(0);
		long[] t2 = newPolynomial.poly.get(1);
		long[] t3 = newPolynomial.poly.get(2);
		long[] t4 = newPolynomial.poly.get(3);
		long[] t5 = newPolynomial.poly.get(4);
		long[] t6 = newPolynomial.poly.get(5);
		System.out.println("T1: " + t1[0] + " AND " + t1[1]);
		System.out.println("T2: " + t2[0] + " AND " + t2[1]);
		System.out.println("T3: " + t3[0] + " AND " + t3[1]);
		System.out.println("T4: " + t4[0] + " AND " + t4[1]);
		System.out.println("T5: " + t5[0] + " AND " + t5[1]);
		System.out.println("T6: " + t6[0] + " AND " + t6[1]);*/
		return newPolynomial;
	}
	
	//STRICTLY FOR TESTING PURPOSES
	public static void main(String[] args) throws IOException
	{
		File tempFile = new File("src/PolynomialFile.txt");
		Polynomial test1 = new Polynomial();
		Polynomial test2 = new Polynomial();
		long[] a = {27,3};
		long[] b = {15,2};
		long[] c = {12,1};
		long[] d = {18,2};
		long[] e = {10,1};
		long[] f = {8,0};
		test1.InsertTerm(a);
		test1.InsertTerm(b);
		test1.InsertTerm(c);
		test2.InsertTerm(d);
		test2.InsertTerm(e);
		test2.InsertTerm(f);
		/*int len = test1.poly.size();
		for (int i = 0; i < len; i++)
		{
			long[] temp = test1.poly.get(i);
			System.out.println("LINKED LIST: " + temp[0] + " AND " + temp[1]);
		}
		test1.getTotalValue(test1, 2);
		Polynomial test3 = test1.add(test2);
		for (int i = 0; i < test3.poly.size(); i++)
		{
			long[] temp = test3.poly.get(i);
			System.out.println("RESULT POLY: " + temp[0] + " AND " + temp[1]);
		}
		test1.differentiate();
		for (int i = 0; i < test1.poly.size(); i++)
		{
			long[] temp = test1.poly.get(i);
			System.out.println("DIFFERENTIATED POLY: " + temp[0] + " AND " + temp[1]);
		}*/
		Polynomial test3 = test1.multiply(test2);
		//for (int i = 0; i < test3.poly.size(); i++)
		//{
			//long[] temp = test3.poly.get(i);
			//System.out.println("Multiplied poly is: " + temp[0] + " AND " + temp[1]);
		//}
	}
}