package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HomePage frame = new HomePage();
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
	public HomePage() {
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setTitle("MWANZOBARAKA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(689, 461));
		setLocationRelativeTo(null);
//	    setBounds(100, 100, 792, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel detailspanel = new JPanel();
		detailspanel.setBackground(new Color(192, 192, 192));
		detailspanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		detailspanel.setBounds(141, 80, 497, 306);
		contentPane.add(detailspanel);
		detailspanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MembID");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 38, 77, 14);
		detailspanel.add(lblNewLabel);
		
		JLabel fname = new JLabel("First_Name");
		fname.setFont(new Font("Algerian", Font.PLAIN, 11));
		fname.setBounds(10, 80, 102, 14);
		detailspanel.add(fname);
		
		JLabel lname = new JLabel("Last_Name");
		lname.setFont(new Font("Algerian", Font.PLAIN, 11));
		lname.setBounds(10, 125, 102, 14);
		detailspanel.add(lname);
		
		JLabel email = new JLabel("Email");
		email.setFont(new Font("Algerian", Font.PLAIN, 11));
		email.setBounds(10, 162, 63, 14);
		detailspanel.add(email);
		
		JLabel contact = new JLabel("Contact");
		contact.setFont(new Font("Algerian", Font.PLAIN, 11));
		contact.setBounds(10, 200, 77, 14);
		detailspanel.add(contact);
		
		JLabel Id = new JLabel();
		Id.setText(Login.Identity);
		Id.setBounds(209, 37, 77, 14);
		detailspanel.add(Id);
		
		JLabel Fname = new JLabel();
		Fname.setText(Login.fname);
		Fname.setBounds(209, 79, 143, 14);
		detailspanel.add(Fname);
		
		JLabel Lname = new JLabel();
		Lname.setText(Login.lname);
		Lname.setBounds(209, 124, 143, 14);
		detailspanel.add(Lname);
		
		JLabel Email = new JLabel();
		Email.setText(Login.emailAddress);
		Email.setBounds(178, 162, 174, 14);
		detailspanel.add(Email);
		
		JLabel Contact = new JLabel();
		Contact.setText(Login.contactNum);
		Contact.setBounds(209, 200, 143, 14);
		detailspanel.add(Contact);
		
		JButton btnNewButton_2 = new JButton("Total_Shares");
		btnNewButton_2.setForeground(new Color(255, 0, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "Your Total Shares Are " +IndivContributions.totalShares+ "sh");
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_2.setBounds(10, 258, 154, 23);
		detailspanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Loan_Status");
		btnNewButton_3.setForeground(new Color(255, 0, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, Loan.loanInfor);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_3.setBounds(302, 258, 143, 23);
		detailspanel.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("Contribution");
		btnNewButton.setForeground(new Color(50, 205, 50));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// contribution class
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							IndivContributions frame = new IndivContributions();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 80, 121, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Loan");
		btnNewButton_1.setForeground(new Color(50, 205, 50));
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Loan Class
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
				
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 127, 121, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("MWANZOBARAKA SELFHELP GROUP");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(212, 11, 298, 28);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Log Out");
		btnNewButton_4.setForeground(new Color(128, 0, 0));
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(btnNewButton_4, "Confirm You Want To Log Out","MwanzoBaraka",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					System.exit(0);
					
				}
				

			}
		});
		btnNewButton_4.setFont(new Font("Algerian", Font.PLAIN, 12));
		btnNewButton_4.setBounds(10, 363, 103, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Group_Loan");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// grouploan class
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GroupLoan frame = new GroupLoan();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				dispose();
			}
		});
		btnNewButton_5.setForeground(new Color(0, 255, 128));
		btnNewButton_5.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton_5.setBounds(10, 222, 121, 23);
		contentPane.add(btnNewButton_5);
	}
}
