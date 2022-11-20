package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField nametextField;
	
	private static Connection connection = null;
	
	public static String Identity,name,contactNum,emailAddress;
	public static String fname,lname;
	
	public static JButton logingBtn;
	
	public static JButton registerBtn;
	private JLabel lnamelbl;
	private JTextField name2textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(378, 248));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 746, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Welcome To MwanzoBaraka login page");
		titleLbl.setBounds(99, 11, 248, 19);
		contentPane.add(titleLbl);
		
		registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				// registration class
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
				
				dispose();
			}
		});
		registerBtn.setBounds(186, 166, 110, 23);
		contentPane.add(registerBtn);
		
		JLabel fnameLbl = new JLabel("Enter First Name");
		fnameLbl.setBounds(10, 65, 125, 14);
		contentPane.add(fnameLbl);
		
		lnamelbl = new JLabel("Enter Second Name");
		lnamelbl.setBounds(10, 111, 125, 14);
		contentPane.add(lnamelbl);
		
		name2textField = new JTextField();
		name2textField.setBounds(159, 108, 132, 20);
		contentPane.add(name2textField);
		name2textField.setColumns(10);
		
		nametextField = new JTextField();
		nametextField.setBounds(159, 62, 132, 20);
		contentPane.add(nametextField);
		nametextField.setColumns(10);
		
		logingBtn = new JButton("Login");
		logingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Fname = nametextField.getText();
				String Lname = name2textField.getText();			
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
					String user = "root";
					String password = "12960";
					
					connection = DriverManager.getConnection(url, user, password);
					//login
					String selectquery = "SELECT FName,LName from mwanzobaraka.registration WHERE FName=? and LName=?";
					PreparedStatement ps = connection.prepareStatement(selectquery);
					ps.setString(1, Fname);
					ps.setString(2, Lname);
					
					ResultSet rs = ps.executeQuery();
				
					if(rs.next()) {
						
						// HomePage
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									HomePage frame = new HomePage();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "You are not a registered member"+"\n"+"Please Register");
						
					}
					
					//checking person login identity
					
					String selectquery2 = "SELECT MembID,FName,LName,Email,PNum from mwanzobaraka.registration WHERE FName=? and LName=?";
					PreparedStatement ps1 = connection.prepareStatement(selectquery2);
					ps1.setString(1, Fname);
					ps1.setString(2, Lname);
	
					ResultSet rs1 = ps1.executeQuery();
					if(rs1.next()==false) {
						
						JOptionPane.showMessageDialog(null,"Invalid");
						
					}else {
						
						Identity = rs1.getString("MembID");
						fname = rs1.getString("FName");
						lname = rs1.getString("LName");
						name = rs1.getString("FName")+" "+rs1.getString("LName");
						emailAddress = rs1.getString("Email");
						contactNum = rs1.getString("PNum");
						
					}
				
					
					ps.execute();
					ps1.execute();
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		logingBtn.setBounds(10, 166, 89, 23);
		contentPane.add(logingBtn);
	}
}
