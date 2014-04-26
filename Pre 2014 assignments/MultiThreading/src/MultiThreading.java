import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MultiThreading extends JFrame implements KeyListener
{
	static final long serialVersionUID = 2;
	public Car car1 = new Car();
	public Car car2 = new Car();
	public Thread thread1 = new Thread(car1);
	public Thread thread2 = new Thread(car2);
	
	public MultiThreading()
	{
		
	}
	
	public static void main(String[] args)
	{
		MultiThreading m1 = new MultiThreading();
		m1.createAndShowGUI();
	}
	
	public void createAndShowGUI()
	{
		MultiThreading frame = new MultiThreading();
		frame.setPreferredSize(new Dimension(1200,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel(new GridLayout());
		frame.setContentPane(contentPane);
		frame.setFocusable(true);
		frame.addKeyListener(frame);
		frame.setResizable(false);
		frame.add(car1);
		frame.add(car2);
		thread1.start();
		thread2.start();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e)
	{
		char key = e.getKeyChar();
		if (key == 'a')
		{
			//Speed up car 1
			car1.speedInt = car1.speedInt + 10;
			car1.changeText("The speed of Car 1 has been increased to: " + car1.speedInt);
			car1.ranger.setValues(0, car1.speedInt, 0, 200);
			car1.revalidate();
			car1.repaint();
			JOptionPane.showMessageDialog(null, "The speed of Car 1 has been increased to: " + car1.speedText.getText());
		}
		else if (key == 's')
		{
			//slow down car 1
			car1.speedInt = car1.speedInt - 10;
			car1.changeText("The speed of Car 1 has been decreased to: " + car1.speedInt);
			car1.ranger.setValues(0, car1.speedInt, 0, 200);
			JOptionPane.showMessageDialog(null, "The speed of Car 1 has been decreased to: " + car1.speedInt);
			car1.revalidate();
			car1.repaint();
		}
		else if (key == 'k')
		{
			//speed up car 2
			car2.speedInt = car2.speedInt + 10;
			car2.changeText("The Speed of car 2 has been increased to: " + car2.speedInt);
			car2.ranger.setValues(0, car2.speedInt, 0, 200);
			JOptionPane.showMessageDialog(null, "The Speed of car 2 has been increased to: " + car2.speedInt);
			car2.repaint();
			car2.revalidate();
		}
		else if (key == 'l')
		{
			//slow down car 2
			car2.speedInt = car2.speedInt - 10;
			car2.changeText("The Speed of car 2 has been decreased to: " + car2.speedInt);
			car2.ranger.setValues(0, car2.speedInt, 0, 200);
			JOptionPane.showMessageDialog(null, "The Speed of car 2 has been decreased to: " + car2.speedInt);
			car2.repaint();
			car2.revalidate();
		}
		else
		{
			
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
}

class Car extends JPanel implements Runnable
{
	public int speedInt = 0;
	public final JLabel speedText = new JLabel();
	JScrollBar ranger = new JScrollBar(JScrollBar.VERTICAL, 0, 10, 0, 200);
	BufferedImage image1;
	
	public void changeText(String text)
	{
		speedText.setText(text);
		speedText.repaint();
	}
	
	public void run()
	{
		addStuff();
	}
	
	public Car()
	{
		setLayout(new GridLayout());
	}
	
	public void addStuff()
	{
		add(ranger);
		speedText.setText("SPEED");
		add(speedText);
	}
}