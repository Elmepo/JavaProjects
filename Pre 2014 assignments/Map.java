import javax.swing.*;
import java.awt.FlowLayout;

public class Map extends JFrame
{
	public Map()
	{
		//Set FlowLayout aligned left with horizontal gap 10 and vertical gap 20 between components
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		
		//Add Labels and Text fields to the frames
		/*add(new JLabel("First Name"));
		add(new JTextField(8));
		add(new JLabel("MI"));
		add(new JTextField(1));
		add(new JLabel("Last Name"));
		add(new JTextField(8));
		*/
	}
	
	public static void setPoint(int x, int y, Map Graph)
	{
		JPanel point = new JPanel();
		point.setLayout(null);
		JButton btn = new JButton("Test");
		Graph.add(btn);
		add(point);
		point.setLocation(x, y);
	}
	
	public static void main(String[] Args)
	{
		Map frame = new Map();
		setPoint(100, 100, frame);
		frame.setTitle("ShowFlowLayout");
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}