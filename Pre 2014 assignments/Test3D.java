import java.util.Arrays;

public class Test3D
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
		Point3D[] points = new Point3D[9];
		for (int i = 0; i < points.length; i++)
		{
			points[i] = new Point3D(x, y, z);
			x++;
			y++;
			z++;
			//System.out.println("X = " + points[i].getX() + " Y = " + points[i].getY() + " Z = " + points[i].getZ());
		}
		max(points);
		min(points);
	}
	
	public static void max(Point3D[] points)
	{
		double maxResult = 0;
		double[] maxPoint1 = {0.0, 0.0, 0.0};
		double[] maxPoint2 = {0.0, 0.0, 0.0};
		for (int i = 0; i < points.length - 1; i++)
		{
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
		System.out.println("maxResult is " + maxResult + "Using point 1: " + Arrays.toString(maxPoint1) + " and point 2: " + Arrays.toString(maxPoint2));
	}
	
	public static void min(Point3D[] points)
	{
		double minResult = 100000000; //As no maximum value was stated, I assumed this was the Max.
		double[] minPoint1 = {0.0, 0.0, 0.0};
		double[] minPoint2 = {0.0, 0.0, 0.0};
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
		System.out.println("minResult is " + minResult + ". Using point 1: " + Arrays.toString(minPoint1) + " and point 2: " + Arrays.toString(minPoint2));
	}
	
	public static double[] testNoArgs()
	{
		Point3D noArgsPoint3D = new Point3D();
		double[] points = {noArgsPoint3D.getX(), noArgsPoint3D.getY(), noArgsPoint3D.getZ()};
		return points;
	}
	
	public static double[] testArgs(double x, double y, double z)
	{
		Point3D argsPoint3D = new Point3D(x, y, z);
		double[] points = {argsPoint3D.getX(), argsPoint3D.getY(), argsPoint3D.getZ()};
		return points;
	}
	
	public static double testDistance(double x, double y, double z)
	{
		/*Point3D point1 = new Point3D(x, y, z);
		double[] points = {point1.x, point1.y, point1.z};
		double distanceToOrigin = Point3D.distance(points);
		return distanceToOrigin;*/
		Point3D point1 = new Point3D(x, y, z);
		double[] points = {x, y, z};
		System.out.println("The Points used were " + Arrays.toString(points));
		double testDistance1 = point1.distance(point1);
		return testDistance1;
	}
	
	public static double testOverloadedDistance(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		Point3D newPoint = new Point3D();
		double[] point1 = {x1, y1, z1};
		System.out.println("The Points used were " + Arrays.toString(point1));
		double[] point2 = {x2, y2, z2};
		System.out.println("The Points used were " + Arrays.toString(point2));
		double testDistance2 = newPoint.distance(point1, point2);
		return testDistance2;
	}
}