import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Map extends JPanel
{	
	static final long serialVersionUID = 2;
	AffineTransform tx = new AffineTransform();
	
	public Map()
	{
		this.addMouseWheelListener(new Zoom());
	}
	
	public JPanel createContentPane()
	{
		//Creating a base JPanel to place everything on
		JPanel rootGUI = new JPanel();
		//Setting the Layout Manager to null to place everything manually.
		rootGUI.setLayout(new BorderLayout());
		rootGUI.setOpaque(true);
		rootGUI.setPreferredSize(new Dimension(600, 600));
		rootGUI.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel newGUI = new JPanel();
		rootGUI.add(newGUI, BorderLayout.CENTER);
		newGUI.setPreferredSize(new Dimension(500,500));
		return rootGUI;
	}
	
	private static void createAndShowGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Task1");
		//Create and set up the content pane
		Map demo = new Map();
		frame.setContentPane(demo.createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		Hospital hDemo = new Hospital();
		//School sDemo = new School();
		frame.add(hDemo);
		//hDemo.setDoubleBuffered(true);
		//frame.add(sDemo);
		//sDemo.setDoubleBuffered(true);
		frame.setVisible(true);
	}
	
	public static void main(String[] Args)
	{
		//Schedule a job for the event-dispatching thread
		//Creating and showing this applications GUI
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
	private class Zoom implements MouseWheelListener
	{
		double scale = 1.0;
		
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL)
			{
				Point2D p1 = e.getPoint();
				Point2D p2 = null;
				try
				{
					p2 = tx.inverseTransform(p1, null);
				}
				catch (NoninvertibleTransformException ex)
				{
					ex.printStackTrace();
					return;
				}
				scale -= (0.1 * e.getWheelRotation());
				scale = Math.max(0.1, scale);
				tx.setToIdentity();
				tx.translate(p1.getX(), p1.getY());
				tx.scale(scale, scale);
				tx.translate(-p2.getX(), -p2.getY());
				Map.this.revalidate();
				Map.this.repaint();
			}
		}
	}
}


