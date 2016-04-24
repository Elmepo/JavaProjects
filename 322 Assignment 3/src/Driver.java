import java.io.File;

public class Driver
{
	public static void main(String[] args)
	{
		System.out.println("MAZE 01");
		System.out.println("----------------------------------------------------------------");
		int[][] maze01 = Maze.ScanIn(new File("src/maze01.mz"));
		System.out.println("MAZE 02");
		System.out.println("----------------------------------------------------------------");
		int[][] maze02 = Maze.ScanIn(new File("src/maze02.mz"));
		System.out.println("MAZE 03");
		System.out.println("----------------------------------------------------------------");
		int[][] maze03 = Maze.ScanIn(new File("src/maze03.mz"));
	}
}
