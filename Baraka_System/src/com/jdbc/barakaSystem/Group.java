package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Group extends JFrame {

	private JPanel contentPane;
	
	private static JLabel membIDlabel,groupnamelabel,groupgenderlabel,groupemaillabel,groupnumlabel;
	
	private static String groupsNM;
	
	private static JComboBox comboBox;
	
	private static Connection connection = null;
	private static JTable groupTable;
	private JScrollPane scrollPane;
	private JButton lnBtn;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Group() {
		setTitle("Group Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(651, 334));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 651, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel MembIDLabel = new JLabel("MembID");
		MembIDLabel.setBounds(10, 11, 49, 14);
		contentPane.add(MembIDLabel);
		
		JLabel NameLabel = new JLabel("Name");
		NameLabel.setBounds(10, 36, 49, 14);
		contentPane.add(NameLabel);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setBounds(10, 61, 49, 14);
		contentPane.add(genderLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(10, 86, 49, 14);
		contentPane.add(emailLabel);
		
		JLabel pNumLabel = new JLabel("Phone_Number");
		pNumLabel.setBounds(10, 111, 100, 14);
		contentPane.add(pNumLabel);
		
		JLabel existingGroupLabel = new JLabel("Choose One Group");
		existingGroupLabel.setBounds(10, 150, 125, 19);
		contentPane.add(existingGroupLabel);
		
		JLabel groupinforLabel = new JLabel();
		groupinforLabel.setText("This are group Members");
		groupinforLabel.setVisible(false);
		groupinforLabel.setBounds(365, 8, 162, 22);
		contentPane.add(groupinforLabel);
		
		// groups name
		String groupsName[] = {"Equity","KCB","Murata"};
		
		comboBox = new JComboBox(groupsName);
		comboBox.setBounds(10, 181, 162, 22);
		contentPane.add(comboBox);
			
		JButton joinBtn = new JButton("Join Group");
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				createConnection();
				
				
				JOptionPane.showMessageDialog(null,"Joined Successfully");
				
				comboBox.setEnabled(false);
				groupinforLabel.setVisible(true);

			}
		});
		joinBtn.setBounds(95, 226, 119, 20);
		contentPane.add(joinBtn);
		
		membIDlabel = new JLabel();
		membIDlabel.setText(Registration.MembIDField.getText());
		membIDlabel.setBounds(95, 11, 66, 14);
		contentPane.add(membIDlabel);
		
		groupnamelabel = new JLabel();
		groupnamelabel.setText(""+Registration.fNameField.getText()+" "+Registration.lNameField.getText());
		groupnamelabel.setBounds(95, 36, 198, 20);
		contentPane.add(groupnamelabel);
		
		groupgenderlabel = new JLabel();
		groupgenderlabel.setText(Registration.gender);
		groupgenderlabel.setBounds(95, 61, 49, 14);
		contentPane.add(groupgenderlabel);
		
		groupemaillabel = new JLabel();
		groupemaillabel.setText(Registration.emailField.getText());
		groupemaillabel.setBounds(105, 86, 188, 14);
		contentPane.add(groupemaillabel);
		
		groupnumlabel = new JLabel();
		groupnumlabel.setText(Registration.pNumField.getText());
		groupnumlabel.setBounds(115, 111, 125, 14);
		contentPane.add(groupnumlabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 41, 366, 131);
		contentPane.add(scrollPane);
		
		groupTable = new JTable();
		scrollPane.setViewportView(groupTable);
		
		lnBtn = new JButton("Login");
		lnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Loan class
//				EventQueue.invokeLater(new Runnable() {
//					public void run() {
//						try {
//							Loan frame = new Loan();
//							frame.setVisible(true);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}); 
				
				dispose();
				
				// login class
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Login frame = new Login();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		lnBtn.setBounds(438, 225, 89, 23);
		contentPane.add(lnBtn);
		
		JButton newmembbtn = new JButton("new member");
		newmembbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				// registaration class
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Registration frame = new Registration();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		newmembbtn.setBounds(95, 263, 119, 23);
		contentPane.add(newmembbtn);
		
	}
	
	static void createConnection() {
		
		// getting user input
		String id = membIDlabel.getText();
		String Name = groupnamelabel.getText();
		String gender = groupgenderlabel.getText();
		String email = groupemaillabel.getText();
		String pNum = groupnumlabel.getText();
		
		String fName = Registration.fNameField.getText();
		String lName = Registration.lNameField.getText();
		String Dor = Registration.currentDateLabel.getText();
		
		String Dob = ((JTextField)Registration.dateChooser.getDateEditor().getUiComponent()).getText();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Equity")) {
				
				String selectQuery = "select* from mwanzobaraka.equitymembers";
				
			String query = "INSERT INTO equitymembers(MembID,Name,Gender,Email,PNum)" + " VALUES(?,?,?,?,?)";
			
			
			// create the mysql insert preparedstatement
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setString(1, id);
		    ps.setString(2, Name);
		    ps.setString(3, gender);
		    ps.setString(4, email);
		    ps.setString(5, pNum);
		    
		    ResultSet rs = ps.executeQuery(selectQuery);
		    ResultSetMetaData rsmd = rs.getMetaData();
		    DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
		    
		    int cols = rsmd.getColumnCount();
		    String colName[] = new String[cols];
		    for(int i=0;i<cols;i++) {
		    	colName[i] = rsmd.getColumnName(i+1);
		    	model.setColumnIdentifiers(colName);
		    	
		    	String mid,name,gend,mail,num;
		    	while(rs.next()) {
		    		
		    		mid = rs.getString(1);
		    		name = rs.getString(2);
		    		gend = rs.getString(3);
		    		mail = rs.getString(4);
		    		num = rs.getString(5);
		    		
		    		String row[] = {mid,name,gend,mail,num};
		    		
		    		model.addRow(row);
		    		
		    	}
		    	
		    }
		    
		    String query1 = "INSERT INTO groupmembers(MembID,Name,Gender,Email,PNum,GroupName)" + " VALUES(?,?,?,?,?,?)";
		 // create the mysql insert preparedstatement
		    PreparedStatement ps1 = connection.prepareStatement(query1);
		    ps1.setString(1, id);
		    ps1.setString(2, Name);
		    ps1.setString(3, gender);
		    ps1.setString(4, email);
		    ps1.setString(5, pNum);
		    ps1.setString(6, "Equity");
		    
		    ps.execute();
		    ps1.execute();
		    
			}
			
			if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("KCB")) {
				
				String selectQuery = "select* from kcbmembers";
				
				String query = "INSERT INTO kcbmembers(MembID,Name,Gender,Email,PNum)" + " VALUES(?,?,?,?,?)";
				
				// create the mysql insert preparedstatement
			    PreparedStatement ps = connection.prepareStatement(query);
			    ps.setString(1, id);
			    ps.setString(2, Name);
			    ps.setString(3, gender);
			    ps.setString(4, email);
			    ps.setString(5, pNum);
			    
			    ResultSet rs = ps.executeQuery(selectQuery);
			    ResultSetMetaData rsmd = rs.getMetaData();
			    DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
			    
			    int cols = rsmd.getColumnCount();
			    String colName[] = new String[cols];
			    for(int i=0;i<cols;i++) {
			    	colName[i] = rsmd.getColumnName(i+1);
			    	model.setColumnIdentifiers(colName);
			    	
			    	String mid,name,gend,mail,num;
			    	while(rs.next()) {
			    		
			    		mid = rs.getString(1);
			    		name = rs.getString(2);
			    		gend = rs.getString(3);
			    		mail = rs.getString(4);
			    		num = rs.getString(5);
			    		
			    		String row[] = {mid,name,gend,mail,num};
			    		
			    		model.addRow(row);
			    		
			    	}
			   
			    }
			    
			    String query1 = "INSERT INTO groupmembers(MembID,Name,Gender,Email,PNum,GroupName)" + " VALUES(?,?,?,?,?,?)";
				 // create the mysql insert preparedstatement
				    PreparedStatement ps1 = connection.prepareStatement(query1);
				    ps1.setString(1, id);
				    ps1.setString(2, Name);
				    ps1.setString(3, gender);
				    ps1.setString(4, email);
				    ps1.setString(5, pNum);
				    ps1.setString(6, "KCB");
			    
			    ps.execute();
			    ps1.execute();
			    
				}
			
			if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Murata")) {
				
				String selectQuery = "select* from muratamembers";
				
				String query = "INSERT INTO muratamembers(MembID,Name,Gender,Email,PNum)" + " VALUES(?,?,?,?,?)";
				
				// create the mysql insert preparedstatement
			    PreparedStatement ps = connection.prepareStatement(query);
			    ps.setString(1, id);
			    ps.setString(2, Name);
			    ps.setString(3, gender);
			    ps.setString(4, email);
			    ps.setString(5, pNum);
			    
			    ResultSet rs = ps.executeQuery(selectQuery);
			    ResultSetMetaData rsmd = rs.getMetaData();
			    DefaultTableModel model = (DefaultTableModel) groupTable.getModel();
			    
			    int cols = rsmd.getColumnCount();
			    String colName[] = new String[cols];
			    for(int i=0;i<cols;i++) {
			    	colName[i] = rsmd.getColumnName(i+1);
			    	model.setColumnIdentifiers(colName);
			    	
			    	String mid,name,gend,mail,num;
			    	while(rs.next()) {
			    		
			    		mid = rs.getString(1);
			    		name = rs.getString(2);
			    		gend = rs.getString(3);
			    		mail = rs.getString(4);
			    		num = rs.getString(5);
			    		
			    		String row[] = {mid,name,gend,mail,num};
			    		
			    		model.addRow(row);
			    		
			    	}
			    	
			    }
			    
			    String query1 = "INSERT INTO groupmembers(MembID,Name,Gender,Email,PNum,GroupName)" + " VALUES(?,?,?,?,?,?)";
				 // create the mysql insert preparedstatement
				    PreparedStatement ps1 = connection.prepareStatement(query1);
				    ps1.setString(1, id);
				    ps1.setString(2, Name);
				    ps1.setString(3, gender);
				    ps1.setString(4, email);
				    ps1.setString(5, pNum);
				    ps1.setString(6, "Murata");
			    
			    ps.execute();
			    ps1.execute();
			    
				}
		    
		    String query2 = "INSERT INTO registration(MembID,FName,LName,DOB,Gender,Email,PNum,DOR)" + " VALUES(?,?,?,?,?,?,?,?)";
			
			// create the mysql insert preparedstatement
		    PreparedStatement ps1 = connection.prepareStatement(query2);
		    ps1.setString(1, id);
		    ps1.setString(2, fName);
		    ps1.setString(3, lName);
		    ps1.setString(4, Dob);
			ps1.setString(5, gender);
		    ps1.setString(6, email);
		    ps1.setString(7, pNum);
		    ps1.setString(8, Dor);
		    
		    ps1.execute();
		    
		    connection.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
