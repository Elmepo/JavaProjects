import javax.swing.*;

public class TestRectangle
{
	public static void main(String[] args)
	{
		Rectangle demoRectangle = new Rectangle();
		demoRectangle.setBase(8);
		demoRectangle.setHeight(6);
		JFrame frame = new JFrame("Demo Rectangle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(demoRectangle.getBase() * 50, demoRectangle.getHeight() * 50);
		frame.setResizable(false);
		int tempInt = demoRectangle.calculateArea();
		String area = String.valueOf(tempInt);
		JLabel label = new JLabel("Area of demoRectangle is " + area);
		frame.add(label);
		frame.setVisible(true);
	}
}