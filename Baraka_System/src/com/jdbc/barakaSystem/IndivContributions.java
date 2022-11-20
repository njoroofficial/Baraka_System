package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class IndivContributions extends JFrame {

	private JPanel contentPane;
	
	private static Connection connection = null;
	private static JTable table;
	private static JTextField sharestextField;
	private static JLabel sharetotalAmount;
	public static int totalShares;
	public static int equityshares,kcbshares,muratashares;
	
	private static String iD;
	
	private static String groupmemberid,groupname;
	
	private static String equitygroup,kcbgroup,muratagroup;
	
	private static int sharesAmount;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {	
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					IndivContributions frame = new IndivContributions();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public IndivContributions() {
		
		setFont(new Font("Algerian", Font.PLAIN, 14));
		setTitle("Contributions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(783, 369));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 430, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Monthly Member Shares");
		titleLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		titleLabel.setBounds(132, 11, 218, 18);
		contentPane.add(titleLabel);
		
		JLabel contIDLabel = new JLabel("MembID");
		contIDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		contIDLabel.setBounds(10, 40, 49, 14);
		contentPane.add(contIDLabel);
		
		JLabel contnameLabel = new JLabel("Name");
		contnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		contnameLabel.setBounds(10, 78, 49, 14);
		contentPane.add(contnameLabel);
		
		JLabel contNumLabel = new JLabel("Contact");
		contNumLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		contNumLabel.setBounds(301, 40, 49, 14);
		contentPane.add(contNumLabel);
		
		JLabel contemailLabel = new JLabel("Email");
		contemailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		contemailLabel.setBounds(301, 78, 49, 14);
		contentPane.add(contemailLabel);
		
		JLabel contshareLabel = new JLabel("Add Your Shares Amount");
		contshareLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		contshareLabel.setBounds(10, 253, 172, 14);
		contentPane.add(contshareLabel);
		
		String amount[] = {"1000"};
		iD = Login.Identity;
		
		JButton updatebtn = new JButton("Show Shares");
		updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.enable(false);
				
				createConnection();
			}
		});
		updatebtn.setBounds(10, 208, 142, 23);
		contentPane.add(updatebtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 114, 749, 72);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel idlbl = new JLabel();
		idlbl.setText(Login.Identity);
		idlbl.setBounds(87, 40, 65, 14);
		contentPane.add(idlbl);
		
		JLabel namelbl = new JLabel();
		namelbl.setText(Login.name);
		namelbl.setBounds(87, 78, 193, 25);
		contentPane.add(namelbl);
		
		JLabel contactlbl = new JLabel();
		contactlbl.setText(Login.contactNum);
		contactlbl.setBounds(403, 40, 139, 18);
		contentPane.add(contactlbl);
		
		JLabel emaillbl = new JLabel();
		emaillbl.setText(Login.emailAddress);
		emaillbl.setBounds(403, 78, 208, 25);
		contentPane.add(emaillbl);
		
		sharestextField = new JTextField();
		sharestextField.setBounds(191, 250, 122, 20);
		contentPane.add(sharestextField);
		sharestextField.setColumns(10);
		
		JButton updateSharesBtn = new JButton("Update");
		updateSharesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(iD.equals(groupmemberid)) {
					
					addIndivEntries();
					createConnection();
					
				}
				else {
					
					checkGroup();
					createConnection();
				
				}
				
				
			}
		});
		updateSharesBtn.setBounds(416, 249, 89, 23);
		contentPane.add(updateSharesBtn);
		
		JLabel totalsharelbl = new JLabel("Total Shares");
		totalsharelbl.setFont(new Font("Calibri", Font.PLAIN, 12));
		totalsharelbl.setBounds(191, 212, 88, 14);
		contentPane.add(totalsharelbl);
		
		sharetotalAmount = new JLabel();
		sharetotalAmount.setBounds(298, 208, 101, 23);
		contentPane.add(sharetotalAmount);
		
		JButton loanbtn = new JButton("Loan");
		loanbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				// loan class
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Loan frame = new Loan();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		loanbtn.setBounds(594, 249, 89, 23);
		contentPane.add(loanbtn);
		
		JButton btnNewButton = new JButton("HomePage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
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
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBounds(559, 9, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	static void addIndivEntries() {
		
		sharesAmount = Integer.parseInt(sharestextField.getText());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			//String query = "INSERT INTO mwanzobaraka.individualshares(Nov)" + " VALUES(?)" + "WHERE MembID = ?";
			
			
			String query = "UPDATE mwanzobaraka.individualshares SET Nov  = ? WHERE MembID = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(query);
			ps1.setInt(1, sharesAmount);
			ps1.setString(2,Login.Identity);
			
		    ps1.executeUpdate();
		    connection.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void createConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			//String query = "INSERT INTO individualshares(Jan,Feb,March,April,May,June,July,August,Sep,Oct,Nov,December)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			String selectQuery = "SELECT * FROM mwanzobaraka.individualshares where MembID = ?";
			
			PreparedStatement ps = connection.prepareStatement(selectQuery);
			ps.setString(1, Login.Identity);
			
			ResultSet rs = ps.executeQuery();

		    ResultSetMetaData rsmd = rs.getMetaData();
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    
		    int cols = rsmd.getColumnCount();
		    String colName[] = new String[cols];
		    for(int i=0;i<cols;i++) {
		    	colName[i] = rsmd.getColumnName(i+1);
		    	model.setColumnIdentifiers(colName);
		    	
		    	String Jan,Feb,March,April,May,June,July,August,Sep,Oct,Nov,Dec;
		    	while(rs.next()) {
		    		
		    		Jan = rs.getString(1);
		    		Feb = rs.getString(2);
		    		March = rs.getString(3);
		    		April = rs.getString(4);		    		
		    		May = rs.getString(5);
		    		June = rs.getString(6);
		    		July = rs.getString(7);
		    		August = rs.getString(8);
		    		Sep = rs.getString(9);
		    		Oct = rs.getString(10);
		    		Nov = rs.getString(11);
		    		Dec = rs.getString(12);
		    		
		    		String row[] = {Jan,Feb,March,April,May,June,July,August,Sep,Oct,Nov,Dec};
		    		
		    		model.addRow(row);
		    	}
		    }
		    
		    String selectquery1 = "SELECT Jan,Feb,March,April,May,June,July,August,Sep,Oct,Nov,December FROM mwanzobaraka.individualshares WHERE MembID=?";
		    PreparedStatement ps1 = connection.prepareStatement(selectquery1);
			ps1.setString(1, Login.Identity);
			
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()==false) {
				
				sharetotalAmount.setText("No Shares");
				
			}else {
				
				totalShares = rs1.getInt("Jan")+rs1.getInt("Feb")+rs1.getInt("March")+rs1.getInt("April")+rs1.getInt("May")+rs1.getInt("June")+rs1.getInt("July")
							 +rs1.getInt("August")+rs1.getInt("Sep")+rs1.getInt("Oct")+rs1.getInt("Nov")+rs1.getInt("December");
				
				sharetotalAmount.setText(String.valueOf(totalShares)+"sh");
				
			}
			
		    ps.execute();
		    ps1.execute();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	static void checkGroup() {
		
		sharesAmount = Integer.parseInt(sharestextField.getText());
		int groupcut = sharesAmount - 200;
		int groupamount = 200;
		
		equitygroup = "Equity";
		kcbgroup = "KCB";
		muratagroup = "Murata";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query4 = "SELECT * from mwanzobaraka.groupmembers WHERE MembID=?";
			PreparedStatement ps5 = connection.prepareStatement(query4);
			ps5.setString(1, iD);
			
			ResultSet rs = ps5.executeQuery();
			
			if(rs.next() == false) {	
				
				JOptionPane.showMessageDialog(null, "Not Group Member");
			}
			else {
				
				groupmemberid = rs.getString("MembID");
				groupname = rs.getString("GroupName");
				
			}
			
			ps5.execute();
			
			String query = "UPDATE mwanzobaraka.individualshares SET Nov  = ? WHERE MembID = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(query);
			ps1.setInt(1, groupcut);
			ps1.setString(2,Login.Identity);
			
		    ps1.executeUpdate();
		    
		    if(iD.equals(groupmemberid) && groupname.equals(muratagroup)) {
		    	
		    String murataquery = "UPDATE mwanzobaraka.muratashares SET Nov  = ? WHERE MembID = ?";
		    PreparedStatement ps7 = connection.prepareStatement(murataquery);
		    ps7.setInt(1,groupamount);
		    ps7.setString(2, Login.Identity);
		    ps7.executeUpdate();
		    
		    }
		    
		    if(iD.equals(groupmemberid) && groupname.equals(equitygroup)) {
		    	
		    String equityquery = "UPDATE mwanzobaraka.equityshares SET Nov  = ? WHERE MembID = ?"; 
		    PreparedStatement ps7 = connection.prepareStatement(equityquery);
		    ps7.setInt(1,groupamount);
		    ps7.setString(2, Login.Identity);
		    ps7.executeUpdate();
		    
		    }
		    if(iD.equals(groupmemberid) && groupname.equals(kcbgroup)) {
		    	
		    String kcbquery = "UPDATE mwanzobaraka.kcbshares SET Nov  = ? WHERE MembID = ?"; 
		    PreparedStatement ps7 = connection.prepareStatement(kcbquery);
		    ps7.setInt(1,groupamount);
		    ps7.setString(2, Login.Identity);
		    ps7.executeUpdate();
		    
		    }
		    
		    
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
