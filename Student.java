public class Student extends Person
{
	public String study;
	public double gpa;
	
	public Student(){}
	
	public String setFields(String fname1, String lname1, String address1, String zipcode1, String number1, String study1, double gpa1)
	{
		this.fname = fname1;
		this.lname = lname1;
		this.address = address1;
		this.zipcode = zipcode1;
		this.number = number1;
		this.study = study1;
		this.gpa = gpa1;
		return "Set";
	}
	
	public static String display(Student student)
	{
		System.out.println("First Name: " + student.fname);
		System.out.println("Last Name: " + student.lname);
		System.out.println("Address: " + student.address);
		System.out.println("ZipCode: " + student.zipcode);
		System.out.println("Number: " + student.number);
		System.out.println("Studying: " + student.study);
		System.out.println("GPA: " + student.gpa);
		return "Info";
	}
}