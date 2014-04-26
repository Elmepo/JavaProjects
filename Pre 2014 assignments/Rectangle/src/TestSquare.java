import javax.swing.*;

public class TestSquare
{
	public static void main(String[] args)
	{
		Square demoSquare = new Square();
		demoSquare.setBase(8);
		JFrame frame = new JFrame("Demo Square");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(demoSquare.getBase() * 25, demoSquare.getBase() * 25);
		frame.setResizable(false);
		int tempInt = demoSquare.calculateArea();
		String area = String.valueOf(tempInt);
		JLabel label = new JLabel("Area of demoSquare is " + area);
		frame.add(label);
		frame.setVisible(true);
	}
}
