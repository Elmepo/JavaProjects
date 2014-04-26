import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subject implements Comparable<Subject>
{
	/*#######################################/
	/#	Scott Gardner						#/
	/# 	4/5/13								#/
	/#	ITC 206 Assignment 3 Task 1			#/
	/# 	A program to create a series of		#/
	/#	Subjects and codes, that is used in #/
	/#	Conjunction with TestSubject.java	#/
	/#######################################*/
	
	String name = "Name";
	String code = "ABC123";
	
	public Subject(String sName, String sCode)
	{
		if (isValid(sCode) == true)
		{
			this.name = sName;
			this.code = sCode;
		}
		else
		{
			System.out.println("Your code was incorrect, please try again with a correct code.");
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public static String getDiscipline(Subject sCode)
	{
		//This getter returns the First 3 characters of a code.
		String code = sCode.getCode();
		String discipline = code.substring(0,3);
		return discipline;
	}
	
	public boolean codeMatches(String sCode)
	{
		//Uses String.matches to compare two codes, one provided in call, and self
		boolean result = this.code.matches(sCode);
		return result;
	}
	
	public String toString()
	{
		String nameAndCode = this.name + ":" + this.code;
		return nameAndCode;
	}
	
	public static ArrayList<String> allDisciplines(ArrayList<Subject> subjects)
	{
		//Returns All 3 character disciplines, sorted alphabetically
		ArrayList<String> unsortedDisciplines = new ArrayList<String>();
		for(int i = 0; i < subjects.size(); i++)
		{
			unsortedDisciplines.add(getDiscipline(subjects.get(i)));
		}
		Collections.sort(unsortedDisciplines);
		return unsortedDisciplines;
	}
	
	public int compareTo(Subject s)
	{
		//Method that uses Comparable to create a sorting order.
		int lastComp = code.compareTo(s.code);
		return (lastComp != 0 ? lastComp : name.compareTo(s.name));
	}
	
	public static ArrayList<Subject> sortDisciplines(ArrayList<Subject> Subjects)
	{
		//Sorts the provided array of subjects and returns this as a new array
		//This was assuming that the Array was to be sorted by subjects. If this wasn't the case, it's a simple fix
		//Simply replace the "code" variable with the "name" variable, and the "name" variable with the "code" variable in the compareTo method.
		Collections.sort(Subjects);
		return Subjects;
	}
	
	public static ArrayList<String> codesPerDiscipline(ArrayList<Subject> Subjects, String discipline)
	{
		//Returns array of all Subject codes within given discipline
		String subjectCode;
		ArrayList<String> codes = new ArrayList<String>();
		for(int i =0; i < Subjects.size(); i++)
		{
			Subject temp1 = Subjects.get(i);
			String temp1Code = getDiscipline(temp1);
			boolean tempbool = temp1Code.matches(discipline);
			if (tempbool == true)
			{
				String newCode1 = temp1.getCode();
				subjectCode = newCode1.substring(3,6);
				codes.add(subjectCode);
			}
		}
		System.out.println("The following Codes are already in " + discipline);
		return codes;
	}
	
	public static boolean isValid(String possibleCode)
	{
		//Returns a bool value regarding if the provided code is a valid subject code
		if (possibleCode.substring(0,3).matches(".*[a-zA-Z]"))
		{
			if (possibleCode.substring(3,6).matches(".*\\d.*"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public static boolean codeExists(ArrayList<Subject> Subjects, String newCode)
	{
		//Returns a boolean value regarding if the provided Subject code exists in the provided array
		for(int i = 0; i < Subjects.size(); i++)
		{
			Subject currentSubject = Subjects.get(i);
			String currentSubjectCode = currentSubject.getCode();
			if (newCode.matches(currentSubjectCode))
			{
				//System.out.println("That Code is already in the system, please enter a new code.");
				return true;
			}
		}
		//System.out.println("Code does not exist yet.");
		return false;
	}
}