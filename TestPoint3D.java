import java.util.Arrays;
//Just a warning. Spaghetti Code everywhere.

public class TestPoint3D
{
	public static void main(String args[])
	{
		//Basic testing of functionality
		System.out.println(Arrays.toString(testNoArgs()));
		System.out.println();
		System.out.println(Arrays.toString(testArgs(10.0, 20.0, 30.0)));
		System.out.println();
		System.out.println(testDistance(10.0, 20.0, 30.0));
		System.out.println();
		System.out.println(testOverloadedDistance(0.0, 0.0, 0.0, 9.0, 9.0, 9.0));
		System.out.println();
		//Creating 10 point3D objects with code
		double x = 0.0;
		double y = 0.0;
		double z = 0.0;
		Point3D[] points = new Point3D[10];		//even though the array starts at [0] for some reason the for loop ended at [8] so in order to get 10
		for (int i = 0; i < points.length; i++)	//numbers I had to use an array of 11 numbers.
		{
			points[i] = new Point3D(x, y, z);
			System.out.println("X = " + points[i].getX() + " Y = " + points[i].getY() + " Z = " + points[i].getZ());
			x++;
			y++;
			z++;
		}
		max(points);
		System.out.println();
		min(points);
	}
	
	public static void max(Point3D[] points)
	{
		System.out.println("Discovering the max distance between two points:");
		//Setting up the variables that will be used
		double maxResult = 0;
		double[] maxPoint1 = {0.0, 0.0, 0.0};
		double[] maxPoint2 = {0.0, 0.0, 0.0};
		for (int i = 0; i < points.length - 1; i++)
		{
			//Checking a single point against every subsequent one
			//Certifiably Spaghetti code.
			Point3D point1 = new Point3D(points[i].getX(), points[i].getY(), points[i].getZ());
			for (int b = 0; b < i + 1; b++)
			{
				Point3D point2 = new Point3D(points[b].getX(), points[b].getY(), points[b].getZ());
				double[] point2Array = {point2.getX(), point2.getY(), point2.getZ()};
				double tempDistance = point1.distance(point2Array);
				//System.out.println(tempDistance);
				if (tempDistance > maxResult)
				{
					maxResult = tempDistance;
					maxPoint1[0] = point1.getX();
					maxPoint1[1] = point1.getY();
					maxPoint1[2] = point1.getZ();
					maxPoint2[0] = point2.getX();
					maxPoint2[1] = point2.getY();
					maxPoint2[2] = point2.getZ();
				}
			}
		}
		System.out.println("Max distance is " + maxResult + ". Using point a: " + Arrays.toString(maxPoint1) + " and point b: " + Arrays.toString(maxPoint2));
	}
	
	public static void min(Point3D[] points)
	{
		System.out.println("Finding the minimum distance between all points:");
		double minResult = 1000000000; //As no maximum value was stated, I assumed this was the Max, as it's approaching the max possible int.
		double[] minPoint1 = {1000.0, 1000.0, 1000.0};
		double[] minPoint2 = {1000.0, 1000.0, 1000.0};
		//The following code is the same as the max code, but with all operators changed to instead check for minimum distance.
		for (int i = 0; i < points.length - 1; i++)
		{
			Point3D point1 = new Point3D(points[i].getX(), points[i].getY(), points[i].getZ());
			for (int b = 0; b < i + 1; b++)
			{
				Point3D point2 = new Point3D(points[b].getX(), points[b].getY(), points[b].getZ());
				double[] point2Array = {point2.getX(), point2.getY(), point2.getZ()};
				double tempDistance = point1.distance(point2Array);
				if (tempDistance < minResult)
				{
					minResult = tempDistance;
					minPoint1[0] = point1.getX();
					minPoint1[1] = point1.getY();
					minPoint1[2] = point1.getZ();
					minPoint2[0] = point2.getX();
					minPoint2[1] = point2.getY();
					minPoint2[2] = point2.getZ();
				}
			}
		}
		System.out.println("Minimum distance is " + minResult + ". Using point a: " + Arrays.toString(minPoint1) + " and point b: " + Arrays.toString(minPoint2));
	}
	
	public static double[] testNoArgs()
	{
		//Basic testing of the noArgs Constructor in Point3D.class. Simply creates an instance of Point3D and gets the x, y, and z
		Point3D noArgsPoint3D = new Point3D();
		double[] points = {noArgsPoint3D.getX(), noArgsPoint3D.getY(), noArgsPoint3D.getZ()};
		System.out.println("Test the noArg constructor:");
		return points;
	}
	
	public static double[] testArgs(double x, double y, double z)
	{
		//Testing the Args constructor in Point3D.class. Same as noArgs, but uses three doubles passed to set the fields.
		Point3D argsPoint3D = new Point3D(x, y, z);
		double[] points = {argsPoint3D.getX(), argsPoint3D.getY(), argsPoint3D.getZ()};
		System.out.println("Test the arg constructor:");
		System.out.println("The points used were: " + x + ", " + y + ", " + z);
		return points;
	}
	
	public static double testDistance(double x, double y, double z)
	{
		//Testing the distance method, checking the distance between the current point and another passed as an argument
		Point3D point1 = new Point3D();
		double[] points = {x, y, z};
		System.out.println("Testing the distance method (Non-overloaded):");
		System.out.println("The Points used were " + Arrays.toString(points));
		double testDistance1 = point1.distance(points);
		return testDistance1;
	}
	
	public static double testOverloadedDistance(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		//Tests the overloaded Distance method. The wording in the assignment was a bit vague, so I went with my assumption that it was meant to be
		//comparing two different points, as opposed to comparing the current point against another set of co-ordinates i.e. point1.distance(1, 2, 3)
		//However if this was the case the code is easily changed to allow such a modification.
		Point3D newPoint = new Point3D();
		double[] point1 = {x1, y1, z1};
		System.out.println("Testing the distance method (Overloaded):");
		System.out.println("The Points used were " + Arrays.toString(point1));
		double[] point2 = {x2, y2, z2};
		System.out.println("The Points used were " + Arrays.toString(point2));
		double testDistance2 = newPoint.distance(point1, point2);
		return testDistance2;
	}
}