
public class Rectangle
{
	private int base;
	private int height;
	private int area;
	
	public Rectangle()
	{
		this.setBase(0);
		this.setHeight(0);
	}
	
	protected void setBase(int desiredBase)
	{
		this.base = desiredBase;
	}
	
	protected void setHeight(int desiredHeight)
	{
		this.height = desiredHeight;
	}
	
	protected int getBase()
	{
		return this.base;
	}
	
	protected int getHeight()
	{
		return this.height;
	}
	
	public int calculateArea()
	{
		this.area = this.getBase() * this.getHeight();
		return this.area;
	}
}
