import javax.swing.*;
import java.io.*;
//import java.nio.file.Files; //Can't remember adding this, might have been added by compiler. Leaving because it might end up
import java.awt.*;			  //End up screwing me during final compilation
import java.awt.image.*;
import javax.imageio.*;
import java.util.Scanner;

public class Hospital extends JPanel
{
	static final long serialVersionUID = 6;
	public static BufferedImage hospitalImage;
	public static BufferedImage schoolImage;
	public static BufferedImage fuelImage;
	
	public Hospital()
	{
		super();
		setSize(500,500);
		try
		{
			hospitalImage = ImageIO.read(new File("hospital.jpg"));
			schoolImage = ImageIO.read(new File("school.jpg"));
			fuelImage = ImageIO.read(new File("fuel.jpg"));
		}
		catch (IOException ex)
		{
			//Not being handled right now
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int x = 0;
		int y = 0;
		
		File hospitalLocations = new File("Locations.txt");
		try
		{
			//Run through the text file and read every line
			Scanner s = new Scanner(hospitalLocations);
			s.useDelimiter(System.getProperty("line.separator"));
			while (s.hasNext())
			{
				//Check the POI identifier, so that only hospitals are painted
				String tempString = s.next();
				if (tempString.substring(0,8).equals("HOSPITAL"))
				{
					//Split the name away from the lat/long, then split the lat/long into x, y, before painting the image
					String[] nameSplit = tempString.split(":");
					String[] latLongSplit = nameSplit[1].split(",");
					x = Integer.parseInt(latLongSplit[0]);
					y = Integer.parseInt(latLongSplit[1]);
					g.drawImage(hospitalImage, x, y, 20, 20, this);
					//super.paint(g);
					repaint();
				}
				else if (tempString.substring(0,6).equals("SCHOOL"))
				{
					String[] nameSplit = tempString.split(":");
					String[] latLongSplit = nameSplit[1].split(",");
					x = Integer.parseInt(latLongSplit[0]);
					y = Integer.parseInt(latLongSplit[1]);
					g.drawImage(schoolImage, x, y, 20, 20, this);
					//super.paint(g);
					repaint();
				}
				else if (tempString.substring(0,4).equals("FUEL"))
				{
					String[] nameSplit = tempString.split(":");
					String[] latLongSplit = nameSplit[1].split(",");
					x = Integer.parseInt(latLongSplit[0]);
					y = Integer.parseInt(latLongSplit[1]);
					g.drawImage(fuelImage, x, y, 20, 20, this);
					repaint();
				}
			}
			s.close();
		}
		catch (IOException ex)
		{
			//Not Handled right now
		}
	}
}
