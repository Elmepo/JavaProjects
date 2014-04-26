public class Point3D
{
	double x = 0.0;
	double y = 0.0;
	double z = 0.0;
	public Point3D()
	{
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public double getZ()
	{
		return this.z;
	}
	
	public double distance(double[] points) //I actualy could have accepted a Point3D object, but realised this too late in coding.
	{
		double deltaX = this.x - points[0];
		double deltaY = this.y - points[1];
		double deltaZ = this.z - points[2];
		//Using pythag theorem to find the distance
		double planarDistance = (double) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		return planarDistance;
		
	}
	
	public double distance(double[] points1, double[] points2)
	{
		double deltaX = points1[0] - points2[0];
		double deltaY = points1[1] - points2[1];
		double deltaZ = points1[2] - points2[2];
		double planarDistance = (double) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		return planarDistance;
	}

}