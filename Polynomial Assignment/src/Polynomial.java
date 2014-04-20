import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.math.*;

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
			total = (long) Math.pow(givenValue, g[1]);
			total = total * g[0];
			runningtotal += total;
		}
		return runningtotal;
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
			}
			newPolynomial = tempPolynomial.add(newPolynomial);
		}
		for (int t = 0; t < newPolynomial.poly.size(); t++)
		{
			long[] temp = newPolynomial.poly.get(t);
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
	/*public static void main(String[] args) throws IOException
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
		Polynomial test3 = new Polynomial();
		test3 = test3.scannerRead();
		//test3 = test3.readFile(tempFile);
		System.out.println(test3.poly.size());
		for (int i = 0; i < test3.poly.size(); i++)
		{
			long[] temp = test3.poly.get(i);
			System.out.println("Read Poly is: " + temp[0] + " AND " + temp[1]);
		}
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
		}
		//Polynomial test3 = test1.multiply(test2);
	}*/
}