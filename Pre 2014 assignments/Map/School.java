import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Scanner;

public class School extends JPanel
{
	static final long serialVersionUID = 1;
	public static BufferedImage schoolImage;
	
	public School()
	{
		super();
		setSize(500, 500);
		try
		{
			schoolImage = ImageIO.read(new File("school.jpg"));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int x = 0;
		int y = 0;
		
		File SchoolLocations = new File("Locations.txt");
		try
		{
			Scanner s = new Scanner(SchoolLocations);
			s.useDelimiter(System.getProperty("line.separator"));
			while (s.hasNext())
			{
				String tempString = s.next();
				if (tempString.substring(0,6).equals("SCHOOL"))
				{
					String[] nameSplit = tempString.split(":");
					String[] latLongSplit = nameSplit[1].split(",");
					x = Integer.parseInt(latLongSplit[0]);
					y = Integer.parseInt(latLongSplit[1]);
					g.drawImage(schoolImage, x, y, 20, 20, this);
					//super.paint(g);
					repaint();
				}
			}
			s.close();
		}
		catch (IOException ex)
		{
			//Later
		}
	}
}