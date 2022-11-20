package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registration_Fee extends JFrame {

	private JPanel contentPane;
	
	private static Connection connection = null;

	private static JLabel currentdateLbl;
	
	public static JButton payBtn;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//	}

	/**
	 * Create the frame.
	 */
	public registration_Fee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(338,205));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 338, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel inforLabel = new JLabel("Personal Information");
		inforLabel.setBounds(98, 11, 127, 31);
		contentPane.add(inforLabel);
		
		JLabel paylabel = new JLabel("Pay A Registration Fee of 2000");
		paylabel.setBounds(10, 69, 186, 43);
		contentPane.add(paylabel);
		
		payBtn = new JButton("Pay 2000");
		payBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,"Registered");
				
				
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
				
				dispose();
				
				if(e.getSource()==payBtn) {
					
					Login.registerBtn.disable();
				}
				
				createConnection();
			}
		});
		payBtn.setBounds(171, 123, 100, 23);
		contentPane.add(payBtn);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setBounds(10, 44, 49, 14);
		contentPane.add(dateLabel);
		
		currentdateLbl = new JLabel();
		currentdateLbl.setText(""+ java.time.LocalDate.now());
		currentdateLbl.setBounds(108, 44, 117, 14);
		contentPane.add(currentdateLbl);
	}
	static void createConnection() {
		
		// getting user input
			String id = Registration.MembIDField.getText();
			int amount = 2000;
			String Dor = currentdateLbl.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO membercontribution(MembID,contAmount,Date)" + " VALUES(?,?,?)";
			
			// create the mysql insert preparedstatement
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setString(1, id);
		    ps.setInt(2, amount);
		    ps.setString(3, Dor);
		    
		    ps.execute();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
