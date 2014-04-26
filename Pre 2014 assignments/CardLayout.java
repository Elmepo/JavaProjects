import javax.swing.*;

public class CardLayout
{
	public static void main(String[] args)
	{
		String endl = System.getProperty("line.separator");
		final ImageIcon CardImage = new ImageIcon(CardLayout.class.getResource("CardPhoto.jpg"));
		JOptionPane.showMessageDialog(null, "Scott Gardner" + endl + endl + "43 Andrews Avenue, Wagga Wagga" + endl + "NSW, 2650" + endl + endl +
										"69223267" + endl + "0487591298", "Business Card", JOptionPane.PLAIN_MESSAGE, CardImage);
	}
}