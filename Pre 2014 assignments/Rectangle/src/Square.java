public class Square extends Rectangle
{
	private int Base;
	private int Area;
	
	public Square()
	{
		setBase(0);
	}
	
	public void setBase(int desiredBase)
	{
		this.Base = desiredBase;
	}
	
	public int getBase()
	{
		return this.Base;
	}
	
	public int calculateArea()
	{
		this.Area = this.Base * this.Base;
		return Area;
	}
}
