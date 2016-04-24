import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JavaTable extends JPanel
{
	static final long serialVersionUID = 1;
	//Setting variables, mostly to be used in creating the GUI, so that we can obtain
	//Button presses and JTextField Data.
	private boolean DEBUG = false;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/ST11489878";
	private JLabel idLabel = new JLabel("Student ID");
	private JLabel nameLabel = new JLabel("Name");
	private JLabel A1Label = new JLabel("Assignment 1");
	private JLabel A2Label = new JLabel("Assignment 2");
	private JLabel A3Label = new JLabel("Assignment 3");
	private JLabel finalLabel = new JLabel("Final");
	private JTextField idField = new JTextField(8);
	private JTextField nameField = new JTextField(7);
	private JTextField A1Field = new JTextField(8);
	private JTextField A2Field = new JTextField(8);
	private JTextField A3Field = new JTextField(8);
	private JTextField finalField = new JTextField(8);
	private JButton searchButton = new JButton("Search By ID");
	private JButton insertButton = new JButton("Add new Student");
	private JButton createButton = new JButton("Create the Database");
	private JButton calculateButton = new JButton("Calculate Overall Score");
	private String[] columnNames = {"ID", "Name", "Assignment 1", "Assignment 2", "Assignment 3", "Final"};
	/*private Object[][] data = {
			{new Integer(1), "Test 1", new Integer(50), new Integer(80), new Integer(60), new Integer(70)},
			{new Integer(2), "Test 2", new Integer(65), new Integer(40), new Integer(10), new Integer(100)}
	};*/
	private Object[][] data = {};
	final JTable table = new JTable(new DefaultTableModel(data, columnNames));
	DefaultTableModel model = (DefaultTableModel) table.getModel();
	private Connection conn = null;
	private Statement stmt = null;
	
	public JavaTable()
	{
		super(new GridLayout());
		MakeTable();
		//MakeDatabase();
		calculateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String idFieldValue = idField.getText();
				/*String a1FieldValue = A1Field.getText();
				String a2FieldValue = A2Field.getText();
				String a3FieldValue = A3Field.getText();
				String finalFieldValue = finalField.getText();*/
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(DB_URL, "root", "abc123");
					stmt = conn.createStatement();
					String sqlQuery = "SELECT Assignment1, Assignment2, Assignment3, Final FROM ITC000 WHERE StudentId = " + idFieldValue;
					ResultSet rSet = stmt.executeQuery(sqlQuery);
					while (rSet.next())
					{
						double a1Weighted = rSet.getInt("Assignment1") * 0.1;
						double a2Weighted = rSet.getInt("Assignment2") * 0.2;
						double a3Weighted = rSet.getInt("Assignment3") * 0.2;
						double finalWeighted = rSet.getInt("Final") * 0.5;
						double totalWeighted = a1Weighted + a2Weighted + a3Weighted + finalWeighted;
						JOptionPane.showMessageDialog(null, "Marks for Student with ID: " + idFieldValue + "\n" +
															"Assignment 1: " + rSet.getInt("Assignment1") + "\n" +
															"Assignment 2: " + rSet.getInt("Assignment2") + "\n" +
															"Assignment 3: " + rSet.getInt("Assignment3") + "\n" +
															"Final Exam: " + rSet.getInt("Final") + "\n" +
															"Total Weighted Mark: " + totalWeighted);
					}
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MakeDatabase();
			}
		});
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String idFieldValue = idField.getText();
				String sqlQuery = "SELECT * FROM ITC000 WHERE StudentId = " + idFieldValue + ";";
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(DB_URL, "root", "abc123");
					stmt = conn.createStatement();
					ResultSet result = stmt.executeQuery(sqlQuery);
					String arr = null;
					while (result.next())
					{
						String id = result.getString("StudentId");
						String name = result.getString("Name");
						String a1 = result.getString("Assignment1");
						String a2 = result.getString("Assignment2");
						String a3 = result.getString("Assignment3");
						String f1 = result.getString("Final");
						arr = "ID: " + id + "\n" + "Name: " + name + "\n" + "Assignment 1 Score: " + a1 + "\n" + "Assignment 2 Score: " + a2 + "\n" + "Assignment 3 Score: " + a3 + "\n" + "Final Exam Score: " + f1;
						JOptionPane.showMessageDialog(null, arr);
					}
					//JOptionPane.showMessageDialog(null, result);
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		insertButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String idFieldValue = idField.getText();
				String nameFieldValue = nameField.getText();
				String A1FieldValue = A1Field.getText();
				String A2FieldValue = A2Field.getText();
				String A3FieldValue = A3Field.getText();
				String finalFieldValue = finalField.getText();
				if (idFieldValue.contains("[a-zA-Z]+") == false && idFieldValue != null)
				{
					if (nameFieldValue != null)
					{
						if (A1FieldValue.contains("[a-zA-Z]+") == false && A1FieldValue != null)
						{
							if (A2FieldValue.contains("[a-zA-Z]+") == false && A2FieldValue != null)
							{
								if (A3FieldValue.contains("[a-zA-Z]+") == false && A3FieldValue != null)
								{
									if (finalFieldValue.contains("[a-zA-Z]+") == false && finalFieldValue != null)
									{
										try
										{
											model.addRow(new Object[]{new Integer(Integer.parseInt(idFieldValue)), nameFieldValue, new Integer(Integer.parseInt(A1FieldValue)), new Integer(Integer.parseInt(A2FieldValue)), new Integer(Integer.parseInt(A3FieldValue)), new Integer(Integer.parseInt(finalFieldValue))});
											Class.forName("com.mysql.jdbc.Driver");
											conn = DriverManager.getConnection(DB_URL, "root", "abc123");
											stmt = conn.createStatement();
											stmt.executeUpdate("USE ST11489878");
											String sqlStatement = "INSERT INTO ITC000 " + "VALUES (" + new Integer(Integer.parseInt(idFieldValue)) + ", " + "'" + nameFieldValue + "'" + ", " + new Integer(Integer.parseInt(A1FieldValue)) + ", " + new Integer(Integer.parseInt(A2FieldValue)) + ", " + new Integer(Integer.parseInt(A3FieldValue)) + ", " + new Integer(Integer.parseInt(finalFieldValue)) + ");";
											stmt.executeUpdate(sqlStatement);
											//stmt.executeUpdate("INSERT INTO ITC000 " +
											//					"VALUES (new Integer(Integer.parseInt(idFieldValue)), nameFieldValue, new Integer(Integer.parseInt(A1FieldValue)), new Integer(Integer.parseInt(A2FieldValue)), new Integer(Integer.parseInt(A3FieldValue)), new Integer(Integer.parseInt(finalFieldValue)))");
											//stmt.executeUpdate("INSERT INTO ITC000 " + "VALUES (5, nameFieldValue, 100, 100, 100, 100)");
											JOptionPane.showMessageDialog(null, "Successfully entered a new Student");
										}
										catch(SQLException se)
										{
											se.printStackTrace();
										}
										catch(Exception e)
										{
											e.printStackTrace();
										}
										finally
										{
											try
											{
												if (stmt!=null)
													stmt.close();
											}
											catch(SQLException se)
											{
												se.printStackTrace();
											}
											try
											{
												if (conn!=null)
													conn.close();
											}
											catch(SQLException se)
											{
												se.printStackTrace();
											}
										}
										//model.addRow(new Object[]{new Integer(Integer.parseInt(idFieldValue)), nameFieldValue, new Integer(Integer.parseInt(A1FieldValue)), new Integer(Integer.parseInt(A2FieldValue)), new Integer(Integer.parseInt(A3FieldValue)), new Integer(Integer.parseInt(finalFieldValue))});
									}
									else
									{
										JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "One or more of the data you entered is incorrect, please check and try again");
				}
				//JOptionPane.showMessageDialog(null, textFieldValue);
			}
		});
	}
	
	public void MakeDatabase()
	{
		try
		{
			JOptionPane.showMessageDialog(null, "Made Database");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost", "root", "abc123");
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE DATABASE ST11489878");
			stmt.executeUpdate("USE ST11489878");
			stmt.executeUpdate("CREATE TABLE ITC000 " +
								"(StudentId Integer not NULL, " +
								" Name VARCHAR(15), " +
								" Assignment1 Integer not NULL, " +
								" Assignment2 Integer not NULL, " +
								" Assignment3 Integer not NULL, " +
								" Final Integer not NULL, " +
								"  PRIMARY KEY ( StudentId ))");
			stmt.executeUpdate("INSERT INTO ITC000 " +
								"VALUES (1, 'Scott', 100, 100, 100, 100)");
			model.addRow(new Object[]{1, "Scott", 100, 100, 100, 100});
			//ResultSet rs = stmt.executeQuery("SELECT * FROM ITC000");
			//System.out.println("ID  Name    A1   A2   A3   Final");
			/*while (rs.next())
			{
				int id = rs.getInt("StudentID");
				String name = rs.getString("Name");
				int A1 = rs.getInt("Assignment1");
				int A2 = rs.getInt("Assignemtn2");
				int A3 = rs.getInt("Assignment3");
				int F1 = rs.getInt("Final");
				System.out.println(id+"   "+name+"  "+A1+"  "+A2+"  "+A3+"  "+F1);
			}*/
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
			}
			catch(SQLException se)
			{
				//nothing
			}
			try
			{
				if(conn!=null)
					conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}//End finally
	}//End Try
	
	public void MakeTable()
	{
		//super(new GridLayout(1,0));

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		if (DEBUG)
		{
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
					}
				});
		}
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void printDebugData(JTable table)
	{
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();
		
		System.out.println("Value of Data: ");
		for (int i=0; i < numRows; i++)
		{
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++)
			{
				System.out.print("  " + model.getValueAt(i,  j));
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}
	
	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("JavaTable");
		//I'm setting the Frame size so large here mostly due to constraints from the JTable on the
		//Left side of the screen. I could have used GridBadLayout to give the JTable a larger space
		//And the Data input form on the right less, but That would require a lot more effort
		frame.setPreferredSize(new Dimension(1150, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JavaTable newContentPane = new JavaTable();
		newContentPane.setOpaque(true); //Content panes must be opaque
		frame.setContentPane(newContentPane);
		JPanel tempPanel = new JPanel(new GridLayout(8, 2));
		tempPanel.setPreferredSize(new Dimension(100, 400));
		frame.getContentPane().add(tempPanel);
		tempPanel.add(newContentPane.idLabel);
		tempPanel.add(newContentPane.idField);
		tempPanel.add(newContentPane.nameLabel);
		tempPanel.add(newContentPane.nameField);
		tempPanel.add(newContentPane.A1Label);
		tempPanel.add(newContentPane.A1Field);
		tempPanel.add(newContentPane.A2Label);
		tempPanel.add(newContentPane.A2Field);
		tempPanel.add(newContentPane.A3Label);
		tempPanel.add(newContentPane.A3Field);
		tempPanel.add(newContentPane.finalLabel);
		tempPanel.add(newContentPane.finalField);
		tempPanel.add(newContentPane.searchButton);
		tempPanel.add(newContentPane.insertButton);
		tempPanel.add(newContentPane.createButton);
		tempPanel.add(newContentPane.calculateButton);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
