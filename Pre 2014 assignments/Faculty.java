public class Faculty extends CollegeEmployee
{
	public boolean permanant;
	
	public Faculty(){}
	
	public String setFields(String fname1, String lname1, String address1, String zipcode1, String number1, String ssn1, String salary1, String deptName1, boolean permanant1)
	{
		this.fname = fname1;
		this.lname = lname1;
		this.address = address1;
		this.zipcode = zipcode1;
		this.number = number1;
		this.ssn = ssn1;
		this.salary = salary1;
		this.deptName = deptName1;
		this.permanant = permanant1;
		return "Set";
	}
	
	public static String display(Faculty person)
	{
		System.out.println("First Name: " + person.fname);
		System.out.println("Last Name: " + person.lname);
		System.out.println("Address: " + person.address);
		System.out.println("Zipcode: " + person.zipcode);
		System.out.println("Number: " + person.number);
		System.out.println("SSN: " + person.ssn);
		System.out.println("Salary: " + person.salary);
		System.out.println("Department Name: " + person.deptName);
		System.out.println("Permanant? [true/false]: " + person.permanant);
		return "Info";
	}
}