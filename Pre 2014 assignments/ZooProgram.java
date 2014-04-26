import javax.swing.*;

public class ZooProgram
{
	public static void main(String[] args)
	{
		boolean lastGroup = false;
		int takings = 0;
		while (lastGroup == false)
		{
			int chosenOption = JOptionPane.showConfirmDialog(null, "Entering a group?", "Zoo Program", JOptionPane.YES_NO_OPTION);
			if (chosenOption == JOptionPane.YES_OPTION)
			{
				int numberOfChildren = Integer.parseInt(JOptionPane.showInputDialog("How many children are in the group? (Age 6 - 15): "));
				int numberOfAdults = Integer.parseInt(JOptionPane.showInputDialog("How many adults are in the group? (Age 16 - 59): "));
				int numberOfSeniors = Integer.parseInt(JOptionPane.showInputDialog("How many seniors are in the group? (Age 60+): "));
				int unaccompaniedChildren = 0;
				int legalGuardians = 0;
				legalGuardians = numberOfAdults + numberOfSeniors;
				if (numberOfChildren > legalGuardians)
				{
				unaccompaniedChildren = numberOfChildren - legalGuardians;
				numberOfChildren -= unaccompaniedChildren;
				}
				int totalCharge = (numberOfAdults * 10) + (numberOfSeniors * 8) + (unaccompaniedChildren * 5) + (numberOfChildren * 2);
				/*(numberOfAdults % numberOfChildren)*/
				takings += totalCharge;
				JOptionPane.showMessageDialog(null, "Total cost for the group is $" + totalCharge + ".", "ZooProgram v. 1.0.0", JOptionPane.PLAIN_MESSAGE);
			}
			else if (chosenOption == JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null, "The total takings for today were $" + takings + ".");
				lastGroup = true;
			}
		}
	}
}