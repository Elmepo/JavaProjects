import javax.swing.*;
import java.util.Arrays;
import java.util.ArrayList;

//Importing the libraries I'll need later, to save me from typing them out below

public class ProcessMarks
{
	public static void main(String[] args)
	{
		//The main function, primarily calls the other functions and displays their results.
		int[] marks = Marks.getMarks();
		System.out.println(max(marks));
		System.out.println(min(marks));
		System.out.println(range());
		System.out.println(mean(marks));
		System.out.println(median());
		System.out.println(mode(marks));
		System.out.println(grades(marks));
		System.out.println(gradeDistn(grades(marks)));
		//System.out.println(Arrays.toString(marks)); //Original array, used for testing purposes.
	}
	private static int max(int[] marks)
	{
		//Just runs through the array, checking each item against the last. If it's bigger, it becomes the new max
		int maxResult = 0;
		for (int count = 0; count < marks.length; count++)
		{
			if (marks[count] > maxResult)
			{
				maxResult = marks[count];
			}
		}
		return maxResult;
	}
	private static int min(int[] marks)
	{
		//Same as max but with min
		int minResult = 100; //Assuming the test marks are percentages, no mark can logically be higher than %100
		for (int count = 0; count < marks.length; count++)
		{
			if (marks[count] < minResult)
			{
				minResult = marks[count];
			}
		}
		return minResult;
	}
	private static int range()
	{
		//Basic math, takes the min from the max and the result is the range of scores.
		int[] marks = Marks.getMarks();
		int maxMarks = max(marks);
		int minMarks = min(marks);
		int rangeOfMarks = maxMarks - minMarks;
		return rangeOfMarks;
	}
	private static double mean(int[] marks)
	{
		//Finds the mean of the scores.
		double total = 0;
		for (double i : marks)
		{
			total += i;
		}
		total = total / marks.length;
		return total;
	}
	private static double median()
	{
		//Finds the median of the numbers
		int[] sortedMarks = Marks.getMarks();
		Arrays.sort(sortedMarks);
		double median;
		if (sortedMarks.length%2 == 0)
		{
			median = ((double)sortedMarks[sortedMarks.length/2] + (double)sortedMarks[sortedMarks.length/2+1])/2;
		}
		else
		{
			median = (double)sortedMarks[sortedMarks.length/2];
		}
		return median;
	}
	private static int mode(int[] marks)
	{
		//Finds the mode of the numbers
		int maxValue = 0;
		int maxCount = 0;
		for (int i = 0; i < marks.length; i++)
		{
			int count = 0;
			for (int j = 0; j < marks.length; ++j)
			{
				if (marks[j] == marks[i]) ++count;
			}
			if (count > maxCount)
			{
				maxCount = count;
				maxValue = marks[i];
			}
		}
		return maxValue;
	}
	private static ArrayList grades(int[] marks)
	{
		//Uses a character ArrayList to go through the array and create a new arraylist filled with the corresponding grades.
		ArrayList<Character> grades = new ArrayList<Character>();
		int[] gradeBoundaries = {85, 75, 65, 50};
		for (int i = 0; i < marks.length; i++)
		{
			if (marks[i] >= gradeBoundaries[0])
			{
				grades.add('A');
			}
			else if (marks[i] < gradeBoundaries[0] && marks[i] >= gradeBoundaries[1])
			{
				grades.add('B');
			}
			else if (marks[i] < gradeBoundaries[1] && marks[i] >= gradeBoundaries[2])
			{
				grades.add('C');
			}
			else if (marks[i] < gradeBoundaries[2] && marks[i] >= gradeBoundaries[3])
			{
				grades.add('D');
			}
			else
			{
				grades.add('E');
			}
		}
		return grades;
	}
	private static String gradeDistn(ArrayList<Character> grades)
	{
		StringBuilder sBuilder = new StringBuilder(grades.size());	/*------------------------------------------------------*/
		for (Character c : grades)									/*			Conversion from ArrayList<Character>		*/
		{															/*						To char[]						*/
			sBuilder.append(c);										/*														*/
		}															/*		Converts the ArrayList of grades to a string	*/
		String sGrades = sBuilder.toString();						/*		    Then converts that String to a char[]		*/
		char[] cGrades = sGrades.toCharArray();						/*------------------------------------------------------*/
		int gradeACount = 0;
		int gradeBCount = 0;
		int gradeCCount = 0;
		int gradeDCount = 0;
		int gradeECount = 0;
		//Counts up the different grade levels and then displays them
		for (int i = 0; i < cGrades.length; i++)
		{
			if (cGrades[i] == 'A')
			{
				gradeACount++;
			}
			else if (cGrades[i] == 'B')
			{
				gradeBCount++;
			}
			else if (cGrades[i] == 'C')
			{
				gradeCCount++;
			}
			else if (cGrades[i] == 'D')
			{
				gradeDCount++;
			}
			else
			{
				gradeECount++;
			}
		}
		String newLine = System.getProperty("line.separator");//Just setting up a newline, since System.getProperty("line.separator") gets annoying to
															  //Type after so many iterations.
		return "A: " + gradeACount + newLine + "B: " + gradeBCount + newLine + "C: " + gradeCCount + newLine + "D: " + gradeDCount + newLine + "E: " + gradeECount;
	}
}