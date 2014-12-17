// FILE: Maze.java
// This program illustrates the use of the traverseMaze method that uses
// recursion to guide a user into a maze and back out again.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
* The <CODE>Maze</CODE> Java application illustrates the use of
* the <CODE>traverseMaze</CODE> method that uses recursion to guide a user
* into a maze and back out again.
* <p><dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../applications/Maze.java">
*   http://www.cs.colorado.edu/~main/applications/Maze.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version
*   Jun 12, 1998
******************************************************************************/
public class Maze
{
   /**
   * The main method activates <CODE>traverseMaze</CODE> and prints a message
   * indicating whether the tapestry was found. The <CODE>String</CODE> 
   * argument (<CODE>args</CODE>) is not used in this implementation.
   **/
   public static void main(String[ ] args)
   {
	  int[][] multi = ScanIn(new File("src/maze01.mz"));
	  /*
      if (traverseMaze( ))
         System.out.println("The tapestry was found.");
      else
         System.out.println("The tapestry was not found.");*/
   }
   
   //TODO Fix border issue. Currently this algorithm will only work with solid borders.
   public static int[][] ScanIn(File mFile)
   {
	   Graph graph = new Graph();
	   int[][] a = new int[1][1];
	   int height = 0;
	   int width = 0;
	   try {
		   BufferedReader reader = new BufferedReader(new FileReader(mFile));
		   String line = null;
		   int cellIncrementer = 0;
		   int lineCounter = 0;//h22+*
		   while ((line = reader.readLine()) != null)
		   {
			   char c = line.charAt(0);
			   if (Character.isDigit(line.charAt(0)))
			   {
				   //Create a graph that's as high as the first number and as wide as the second, then label each
				   //vertex numerically, left to right, top to bottom
				   String[] sizes = line.split(" ");
				   height = Integer.parseInt(sizes[0]);
				   width = Integer.parseInt(sizes[1]);
				   graph.setEdges(height, width);
				   graph.setVertexSize(height, width);
				   graph.fill();
				   lineCounter++;
			   }
			   if (lineCounter < ((height * 2) + 1))
			   {
				   if (c == '+')
				   {
					   //Count the number of whitespaces or dashes
					   // /s|-
					   //Pattern pattern = Pattern.compile("\\s"); //regex for a whitespace character
					   //Matcher matcher = pattern.matcher(line);
					   //TODO: Figure out how to work this for everyline, probably do it by implementing a counter,
					   //to keep track of each position. i currently keeps track of position on line, not through graph
					   //as a whole. Refer to notes.
					   int b = 0;
					   for (int i = 1; i < line.length(); i = i + 2)
					   {
						   //Matcher matcher = pattern.matcher(line.charAt(i));
						   //if (matcher.find())
						   if (line.charAt(i) == ' ' || line.charAt(i) == '*')
						   {
							   /* Takes the current cell position, counted by the for loop, and adds it to the
							    * width incrementer to get the current position. Then creates an edge using the current
							    * cell and the cell above it, using (currentCell - mazeWidth) */
							   int currentCell = cellIncrementer + (i-b);
							   //int currentCell = (int) graph.getLabel(i);
							   int ceilingCell = currentCell - width;
							   //int t1 = (int) graph.getLabel(currentCell);
							   //int t2 = (int) graph.getLabel(ceilingCell);
							   graph.addEdge(currentCell, ceilingCell);
							   //Graph.depthFirstPrint(graph, 1);
						   }
						   b++;
					   }
					   lineCounter++;
					   //cellIncrementer = cellIncrementer + width;
				   	}
				   	else if (c == '|')
				   	{
					   for (int i = 2; i < (line.length() - 2); i = i + 2)
					   {
						   //while (matcher.find())
						   if (line.charAt(i) == ' ' || line.charAt(i) == '*')
						   {
							   int currentCell = cellIncrementer + (i/2);
							   int nextCell = currentCell + 1;
							   graph.addEdge(currentCell, nextCell);
						   }
					   }
					   cellIncrementer = cellIncrementer + width;
					   lineCounter++;
				   	}
				   	else if (c ==' ')
				   	{
					   for (int i = 2; i < (line.length() - 2); i = i + 2)
					   {
						   //while (matcher.find())
						   if (line.charAt(i) == ' ' || line.charAt(i) == '*')
						   {
							   int currentCell = cellIncrementer + (i/2);
							   int nextCell = currentCell + 1;
							   graph.addEdge(currentCell, nextCell);
						   }
					   }
					   cellIncrementer = cellIncrementer + width;
					   lineCounter++;
				   	}
			   	}
		   }
		   reader.close();
	   }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
	   Graph.depthFirstPrint(graph, 1);
	   return a;
   }

   /**
   * Determines whether the person traversing the maze has reached a dead end.
   * @param - none
   * <dt><B>Postcondition:</b><dd>
   *   The return value is <CODE>true</CODE> if the direction directly in front
   *   of the user
   *   is a dead end (that is, a direction that cannot contain the tapestry).
   **/
   public static boolean deadend( )
   {
      return inquire("Are you facing a wall?")
             ||
             inquire("Is your name written in front of you?");
   }
   
   
   /**
   * Asks a yes/no question and reads the answer.
   * @param <CODE>query</CODE>
   *   a question to ask
   * <dt><b>Postcondition:</b><dd>
   *   The <CODE>query</CODE> has been printed to <CODE>System.out</CODE>, and
   *   a one-character response read from <CODE>System.in</CODE> (skipping any
   *   whitespace characters. The method returns <CODE>true</CODE> if the 
   *   user’s response was ‘Y’ or ‘y’, and returns <CODE>false</CODE> if the 
   *   user’s response was ‘N’ or ‘n’.
   *   (If the response is some other character, then the query is repeated and 
   *   a new answer is read.)
   **/
   public static boolean inquire(String query)
   {
      char answer = 'N';
      
      do
      { 
         System.out.println(query + " [Y or N]");
         try
         { 
            do
               answer = (char) System.in.read( );
            while (Character.isWhitespace(answer));
         } 
         catch (IOException e)
         { 
            System.err.println("Standard input error: " + e);
            System.exit(0);
         } 
         answer = Character.toUpperCase(answer);
      }  while ((answer != 'Y') && (answer != 'N'));

      return (answer == 'Y');
   }
   
       
   /**
   * Provides interactive help to guide a user through a maze and back out.
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   The user of the program is facing an unblocked spot in the maze, and
   *   this spot has not previously been visited by the user.
   * <dt><b>Postcondition:</b><dd>
   *   The method has asked a series of queries, and provided various
   *   directions to the user. The queries and directions have led the user
   *   through the maze and back to the exact same position where the user
   *   started. If there was a magic tapestry that could be reached in
   *   the maze, then the user has picked up this tapestry and the method
   *   returns <CODE>true</CODE>; otherwise the method returns 
   *   <CODE>false</CODE>.
   **/
   public static boolean traverseMaze( )
   {
      int direction;  // Counts 1, 2, 3 for the three directions to explore
      boolean found;  // Will be set to true if we find the tapestry

      System.out.println("Step forward & write your name on the ground.");
      found = inquire("Have you found the tapestry?");

      if (found)
      {  // Pick up the tapestry and step back from whence you came.
         System.out.println("Pick up the tapestry and take a step backward.");
      }
      else
      {  // Explore the three directions (not counting the one that you just came from). Start 
         // with the direction on your left, and then turn through each of the other directions.
         System.out.println("Please turn left 90 degrees.");
         for (direction = 1; direction <= 3; direction++)
         { 
            if ( !found && !deadend( ) )
                  found = traverseMaze( );
            System.out.println("Please turn right 90 degrees.");
         } 
         // You’re now facing the direction from whence you came, so step forward and turn
         // around. This will put you in the same spot when the method was activated.
         System.out.println("Please step forward, then turn 180 degrees.");
      }
      return found;
   }
    
}