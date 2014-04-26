import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class TestSubject
{
	public static void main(String args[])
	{
		/*String temp = "Test String:Test String 2";
		String[] str_array = temp.split(":");
		System.out.println(str_array[0]);
		System.out.println(str_array[1]);*/
		try
		{
			File subjectFile = new File("SUBJECTS.txt");
			//BufferedReader input = new BufferedReader(new FileReader(subjectFile));
			//BufferedWriter output = new BufferedWriter(new FileWriter(subjectFile));
			ArrayList<Subject> subjectArray = new ArrayList<Subject>();
			Console console = System.console();
			//Scanner s = new Scanner(subjectFile);
			//s.useDelimiter(":");
			if (subjectFile.exists() == false)
			{
				subjectFile.createNewFile();
				BufferedReader input = new BufferedReader(new FileReader(subjectFile));
				BufferedWriter output = new BufferedWriter(new FileWriter(subjectFile));
				String cont = "yes";
				while (cont.matches("yes"))
				{
					System.out.println(Subject.allDisciplines(subjectArray));
					String discipline = console.readLine("Please enter a discipline to see all of it's subject Codes: ");
					System.out.println(Subject.codesPerDiscipline(subjectArray, discipline));
					String newName = console.readLine("Please enter the name of the Subject: ");
					String newCode = console.readLine("Please enter the subject code: ");
					if (Subject.isValid(newCode) == true)
					{
						if (Subject.codeExists(subjectArray, newCode) == false)
						{
							Subject newSubject = new Subject(newName, newCode);
							output.write(newSubject.toString());
							output.write(System.getProperty("line.separator"));
							subjectArray.add(newSubject);
						}
						else
						{
							System.out.println("That Code is already in use, please try again with another code.");
						}
					}
					else
					{
						System.out.println("That Code was not valid, it must be in the format XYZ123, please try again: ");
					}
					cont = console.readLine("Do you wish to add another subject [yes/no]: ");
				}
				output.close();
			}
			else
			{
				//BufferedReader input = new BufferedReader(new FileReader(subjectFile));
				//BufferedWriter output = new BufferedWriter(new FileWriter(subjectFile));
				FileWriter write = new FileWriter(subjectFile, true);
				PrintWriter output = new PrintWriter(write);
				Scanner s = new Scanner(subjectFile);
				s.useDelimiter(System.getProperty("line.separator"));
				while (s.hasNext())
				{
					String tempString = s.next();
					String[] str_array = tempString.split(":");
					Subject tempSubject = new Subject(str_array[0], str_array[1]);
					subjectArray.add(tempSubject);
				}
				//System.out.println(subjectArray);
				String cont = "yes";
				while (cont.matches("yes"))
				{
					System.out.println(Subject.allDisciplines(subjectArray));
					String discipline = console.readLine("Please enter a discipline to see all of it's subject Codes: ");
					System.out.println(Subject.codesPerDiscipline(subjectArray, discipline));
					String newName = console.readLine("Please enter the name of the Subject: ");
					String newCode = console.readLine("Please enter the subject code: ");
					if (Subject.isValid(newCode) == true)
					{
						if (Subject.codeExists(subjectArray, newCode) == false)
						{
							Subject newSubject = new Subject(newName, newCode);
							//output.write(newSubject.toString());
							//output.write(System.getProperty("line.separator"));
							System.out.println(newSubject.toString());
							output.println(newSubject.toString());
							//output.print(System.getProperty("line.separator"));
							subjectArray.add(newSubject);
						}
						else
						{
							System.out.println("That Code is already in use, please try again with another code.");
						}
					}
					else
					{
						System.out.println("That Code was not valid it must be in the format XYZ123, please try again.");
					}
					cont = console.readLine("Do you wish to add another subject [yes/no]: ");
				}
				output.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
		/*Subject subject = new Subject("Name1", "HIJ789");
		String test = Subject.getDiscipline(subject);
		System.out.println(test);
		boolean firstSubject = subject.codeMatches("EFG456");
		boolean secondSubject = subject.codeMatches("HIJ789");
		System.out.println("First " + firstSubject);
		System.out.println("Second " + secondSubject);
		System.out.println(subject.toString());
		Subject subject1 = new Subject("Name2", "AAA000");
		Subject subject2 = new Subject("Name4", "CCC222");
		Subject subject3 = new Subject("Name3", "CCC111");
		ArrayList<Subject> subjectArrayList = new ArrayList<Subject>();
		subjectArrayList.add(subject1);
		subjectArrayList.add(subject2);
		subjectArrayList.add(subject3);
		System.out.println(Subject.allDisciplines(subjectArrayList));
		System.out.println(Subject.codesPerDiscipline(subjectArrayList, "CCC"));
		System.out.println(Subject.isValid("ABC123"));
		System.out.println(Subject.codeExists(subjectArrayList, "CCC111"));
		System.out.println(Subject.codeExists(subjectArrayList, "AAA333"));
		System.out.println(Subject.sortDisciplines(subjectArrayList));*/
}