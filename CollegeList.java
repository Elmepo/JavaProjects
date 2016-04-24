import java.util.Scanner;
import java.io.Console;
import java.util.ArrayList;

public class CollegeList
{
	public static void main(String[] args)
	{
	//Scanner input = new Scanner(System.in);
	//Scanner input2 = new Scanner(System.in);
	Console console = System.console();
	ArrayList<CollegeEmployee> collegeEmployees = new ArrayList<CollegeEmployee>();
	int cECount = 0;
	ArrayList<Faculty> faculty = new ArrayList<Faculty>();
	int fCount = 0;
	ArrayList<Student> students = new ArrayList<Student>();
	int sCount = 0;
	//System.out.println("Start entering data: Must have 4 Employees(C), 3 Faculty (F), and 7 Students(S): ");
	//String choice = input2.next();
	//System.out.println("TESTMOTHAFUCKER");
	boolean loop = true;
	while (loop = true)
	{
		String choice = console.readLine("Start entering data: Must have 4 Employees(C), 3 Faculty (F), and 7 Students(S): ");
		if (choice.matches("C"))
		{
			//System.out.println("TEST!!!!!!");
			if (cECount < 4)
			{
				//System.out.println("TEST");
				String fname = console.readLine("First Name: ");//input.next();
				String lname = console.readLine("Last Name: ");//input.next();
				String address = console.readLine("Address: ");//input.next();
				String zipcode = console.readLine("Zipcode: ");//input.next();
				String number = console.readLine("Number: ");//input.next();
				String ssn = console.readLine("SSN: ");//input.next();
				String salary = console.readLine("Salary: ");//input.next();
				String deptName = console.readLine("Department Name: ");//input.next();
				//System.out.println(cECount);
				CollegeEmployee tempCE = new CollegeEmployee();
				tempCE.setFields(fname, lname, address, zipcode, number, ssn, salary, deptName);
				collegeEmployees.add(tempCE);
				cECount++;
				continue;
			}
			else
			{
				System.out.println("You cannot enter any more Employees");
				continue;
			}
		}
		if (choice.matches("F"))
		{
			if (fCount < 3)
			{
				String fname = console.readLine("First Name: ");//input.next();
				String lname = console.readLine("Last Name:");////input.next();
				String address = console.readLine("Address: ");////input.next();
				String zipcode = console.readLine("Zipcode: ");////input.next();
				String number = console.readLine("Number: ");////input.next();
				String ssn = console.readLine("SSN: ");////input.next();
				String salary = console.readLine("Salary: ");////input.next();
				String deptName = console.readLine("Department Name: ");////input.next();
				String tempForBool = console.readLine("Permanant[true/false]: ");
				Boolean permanant = Boolean.valueOf(tempForBool);////input.nextBoolean();
				Faculty tempFaculty = new Faculty();
				tempFaculty.setFields(fname, lname, address, zipcode, number, ssn, salary, deptName, permanant);
				faculty.add(tempFaculty);
				fCount++;
				continue;
			}
			else
			{
				System.out.println("You cannot enter any more Faculty Members");
				continue;
			}
		}
		if (choice.matches("S"))
		{
			if (sCount < 7)
			{
				String fname = console.readLine("First Name: ");//input.next();
				String lname = console.readLine("Last Name: ");//input.next();
				String address = console.readLine("Address: ");//input.next();
				String zipcode = console.readLine("Zipcode: ");//input.next();
				String number = console.readLine("Number: ");//input.next();
				String major = console.readLine("Major: ");//input.next();
				double gpa = Double.parseDouble(console.readLine("GPA: "));//input.nextDouble();
				Student tempStudent = new Student();
				tempStudent.setFields(fname, lname, address, zipcode, number, major, gpa);
				students.add(tempStudent);
				sCount++;
				continue;
			}
			else
			{
				System.out.println("You cannot enter any more Students");
				continue;
			}
		}
		/*if (choice == "Q")
		{
			System.out.println("TEST");
			loop = false;
			break;
		}*/
		else
		{
			//Don't look at me like that. choice == "Q" wasn't working. It's the same thing though. I mean, if they enter Q it'll still follow
			//This path.
			break;
		}
	}
	for(int i = 0; i < collegeEmployees.size(); i++)
	{
		System.out.println("College Employee #" + i);
		CollegeEmployee.display(collegeEmployees.get(i));
	}
	System.out.println("-----------------------------");
	for(int i = 0; i < faculty.size(); i++)
	{
		System.out.println("Faculty #" + i);
		Faculty.display(faculty.get(i));
	}
	System.out.println("-----------------------------");
	for(int i = 0; i < students.size(); i++)
	{
		System.out.println("Student #" + i);
		Student.display(students.get(i));
	}
	//System.out.println("AAAAAA");
	}
}