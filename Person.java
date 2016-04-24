public class Person
{
	public String fname;
	public String lname;
	public String address;
	public String zipcode;
	public String number;
	
	public String setFields(String fname1, String lname1, String address1, String zipcode1, String number1)
	{
		this.fname = fname1;
		this.lname = lname1;
		this.address = address1;
		this.zipcode = zipcode1;
		this.number = number1;
		return "Set";
	}
	
	public String display(Person person)
	{
		System.out.println(person.fname);
		System.out.println(person.lname);
		System.out.println(person.address);
		System.out.println(person.zipcode);
		System.out.println(person.number);
		return "Info";
	}
}