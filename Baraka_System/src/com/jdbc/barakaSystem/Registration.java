package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

public class Registration extends JFrame {

	private JPanel contentPane;
	public static JTextField MembIDField,fNameField,lNameField,emailField,pNumField;
	
	public static JRadioButton maleGenderRadio,femaleGenderRadio,joinNoRadio;
	
	public static JLabel currentDateLabel;
	
	public static JDateChooser dateChooser;
	
	public static String gender;
	
	private static Connection connection = null;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//	}

	/**
	 * Create the frame.
	 */
	public Registration() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(462, 460));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 556, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel inforLabel1 = new JLabel("Registration of New Members");
		inforLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		inforLabel1.setBounds(77, 11, 287, 26);
		contentPane.add(inforLabel1);
		
		JLabel MembIDLabel = new JLabel("MembID");
		MembIDLabel.setBounds(23, 65, 76, 14);
		contentPane.add(MembIDLabel);
		
		JLabel fNameLabel = new JLabel("First_Name");
		fNameLabel.setBounds(23, 99, 76, 14);
		contentPane.add(fNameLabel);
		
		JLabel lNameLabel = new JLabel("Last_Name");
		lNameLabel.setBounds(23, 133, 76, 14);
		contentPane.add(lNameLabel);
		
		JLabel birthlabel = new JLabel("Birth_Date");
		birthlabel.setBounds(23, 164, 76, 14);
		contentPane.add(birthlabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(23, 199, 76, 14);
		contentPane.add(emailLabel);
		
		JLabel genderlabel = new JLabel("Gender");
		genderlabel.setBounds(23, 224, 76, 14);
		contentPane.add(genderlabel);
		
		JLabel pNumLabel = new JLabel("Phone_Number");
		pNumLabel.setBounds(23, 258, 108, 14);
		contentPane.add(pNumLabel);
		
		JLabel regDateLabel = new JLabel("Date");
		regDateLabel.setBounds(23, 295, 76, 14);
		contentPane.add(regDateLabel);
		
		// restricting user input to integer
		MembIDField = new JTextField();
		MembIDField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				String userId = MembIDField.getText();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
					MembIDField.setEditable(true);
				}
				else {
					MembIDField.setEditable(false);
				}
			}
		});
		
		MembIDField.setBounds(139, 62, 96, 20);
		contentPane.add(MembIDField);
		MembIDField.setColumns(10);
		
		fNameField = new JTextField();
		fNameField.setBounds(139, 96, 144, 20);
		contentPane.add(fNameField);
		fNameField.setColumns(10);
		
		lNameField = new JTextField();
		lNameField.setBounds(139, 130, 144, 20);
		contentPane.add(lNameField);
		lNameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(139, 196, 144, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		// restricting user input to integer
		pNumField = new JTextField();
		pNumField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				String phoneNumber = pNumField.getText();
				if(e.getKeyChar() >= '0' && e.getKeyChar() <='9') {
					pNumField.setEditable(true);
				}
				else {
					pNumField.setEditable(false);
				}
			}
		});
		
		pNumField.setBounds(141, 255, 142, 20);
		contentPane.add(pNumField);
		pNumField.setColumns(10);
		
		
		maleGenderRadio = new JRadioButton("male");
		maleGenderRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(maleGenderRadio.isSelected()) {
					gender = "male";
				}
			}
		});
		maleGenderRadio.setBounds(139, 220, 56, 23);
		contentPane.add(maleGenderRadio);
		
	    femaleGenderRadio = new JRadioButton("female");
	    femaleGenderRadio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if(femaleGenderRadio.isSelected()) {
					gender = "female";
				}
	    	}
	    });
		femaleGenderRadio.setBounds(215, 220, 68, 23);
		contentPane.add(femaleGenderRadio);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(maleGenderRadio);
		bg.add(femaleGenderRadio);
		
		currentDateLabel = new JLabel();
		currentDateLabel.setText(""+ java.time.LocalDate.now());
		currentDateLabel.setBounds(139, 295, 133, 14);
		contentPane.add(currentDateLabel);
		
		JLabel errorLabel = new JLabel();
		errorLabel.setBounds(23, 385, 144, 14);
		contentPane.add(errorLabel);
		
		JLabel joinGroupLabel = new JLabel("Do You Want Join A Group?");
		joinGroupLabel.setBounds(23, 328, 172, 14);
		contentPane.add(joinGroupLabel);
		
		JRadioButton joinYesRadio = new JRadioButton("Yes");
		joinYesRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(MembIDField.getText().isEmpty() || fNameField.getText().isEmpty() ||lNameField.getText().isEmpty()||pNumField.getText().isEmpty()) {
					errorLabel.setText("Error! Empty Field");
					joinYesRadio.setSelected(false);
				}
				else {	
					joinYesRadio.setSelected(true);
					
					dispose();
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Group frame = new Group();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		joinYesRadio.setBounds(23, 349, 56, 23);
		contentPane.add(joinYesRadio);
		
		joinNoRadio = new JRadioButton("No");
		joinNoRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(joinNoRadio.isSelected()) {
					
					if(MembIDField.getText().isEmpty() || fNameField.getText().isEmpty() ||lNameField.getText().isEmpty()||pNumField.getText().isEmpty()) {
						errorLabel.setText("Error! Empty Field");
						joinNoRadio.setSelected(false);
					}
					
					else {
					
					JOptionPane.showMessageDialog(null,"Your Will Be Registered As A Member");
					joinNoRadio.setSelected(true);
					joinYesRadio.setEnabled(false);
					}
				}
			}
		});
		joinNoRadio.setBounds(81, 349, 56, 23);
		contentPane.add(joinNoRadio);
		
		bg.add(joinNoRadio);
		bg.add(joinYesRadio);
		
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(MembIDField.getText().isEmpty() || fNameField.getText().isEmpty() ||lNameField.getText().isEmpty()||pNumField.getText().isEmpty()) {
					errorLabel.setText("Error! Empty Field");
				}
				else {
				
				JOptionPane.showMessageDialog(null,"Registration Successful");
				
				MembIDField.setEditable(false);
				fNameField.setEditable(false);
				lNameField.setEditable(false);
				emailField.setEditable(false);
				pNumField.setEditable(false);
				maleGenderRadio.setSelected(false);
				femaleGenderRadio.setSelected(false);
				registerBtn.setEnabled(false);
				
				createConnection();
				
				
				//registration fee
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							registration_Fee frame = new registration_Fee();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
				
				}
				
			}
		});
		registerBtn.setBounds(227, 366, 96, 23);
		contentPane.add(registerBtn);	
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(139, 158, 144, 20);
		contentPane.add(dateChooser);
		
	}
	
	static void createConnection() {
		
		// getting user input
		String id = MembIDField.getText();
		
		String fName = fNameField.getText();
		
		String lName = lNameField.getText();
		
		String Dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
		
		String email = emailField.getText();
		
		String pNum = pNumField.getText();
		
		String Dor = currentDateLabel.getText();
		
		String Name = fNameField.getText()+" "+lNameField.getText();
				
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO registration(MembID,FName,LName,DOB,Gender,Email,PNum,DOR)" + " VALUES(?,?,?,?,?,?,?,?)";
			
			// create the mysql insert preparedstatement
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setString(1, id);
		    ps.setString(2, fName);
		    ps.setString(3, lName);
		    ps.setString(4, Dob);
			ps.setString(5, gender);
		    ps.setString(6, email);
		    ps.setString(7, pNum);
		    ps.setString(8, Dor);
		    
		    ps.execute();
		    		
		    String query2 = "INSERT INTO members(MembID,FName,LName,Gender,Email,PNum)" + " VALUES(?,?,?,?,?,?)";
		    PreparedStatement ps2 = connection.prepareStatement(query2);
		    ps2.setString(1, id);
		    ps2.setString(2, fName);
		    ps2.setString(3, lName);
		    ps2.setString(4, gender);
		    ps2.setString(5, email);
		    ps2.setString(6, pNum);
				    
		    ps2.execute();						
		    
		    connection.close();
		    
		    
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
