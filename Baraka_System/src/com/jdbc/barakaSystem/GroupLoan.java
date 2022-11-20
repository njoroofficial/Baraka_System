package com.jdbc.barakaSystem;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GroupLoan extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	private static JLabel sharelbl;
	private static JLabel durationlbl;
	private static JLabel InterestLabel;
	private static JLabel rpayAmountlbl;
	private static JLabel namelbl,loanLbl,borrowdatelbl;
	private static JLabel interestLbl;
	private static JLabel repayamount;
	private static JLabel dateLbl;
	
	private static Connection connection = null;
	
	private static JDateChooser dateChooser;
	
	private static JLabel grupnamelbl;
	
	public static int equityTotalShares,kcbTotalShares,murataTotalShares;
	
	private static float groupInterestRate = (float) 0.008;
	private static float interest,totalAmount;
	private static float payinterest,repaytotal;
	
	private static Long month_gap,repaymonth_gap;
	
	private static String setdate,todaydate;
	
	private static int loan;
	
	private static int shareAmount;
	
	private static int loanamount;
	
	private static String payment;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GroupLoan frame = new GroupLoan();
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
	public GroupLoan() {
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setTitle("GROUP LOAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(616, 416));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane ApplytabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ApplytabbedPane.setBounds(10, 11, 579, 326);
		contentPane.add(ApplytabbedPane);
		
		JPanel Apply = new JPanel();
		ApplytabbedPane.addTab("Apply Loan", null, Apply, null);
		Apply.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Group");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 15, 102, 14);
		Apply.add(lblNewLabel);
		
		// groups name
		String groupsName[] = {"Equity","KCB","Murata"};
				
		JComboBox comboBox = new JComboBox(groupsName);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		comboBox.setBounds(147, 11, 102, 22);
		Apply.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(368, 15, 72, 14);
		Apply.add(lblNewLabel_2);
		
		JLabel datelbl = new JLabel();
		datelbl.setText(""+ java.time.LocalDate.now());
		datelbl.setBounds(450, 15, 104, 14);
		Apply.add(datelbl);
		
		JLabel lblNewLabel_4 = new JLabel("Shares");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 63, 79, 14);
		Apply.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Loan_Amount");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(10, 99, 79, 14);
		Apply.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Select Repay Date");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(10, 158, 102, 14);
		Apply.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("Show Interest");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//interest = principal(loan)*rate*period(months)
				
				loan = Integer.parseInt(textField.getText());
				
				interest = loan*groupInterestRate*month_gap;
				
				InterestLabel.setText(""+String.valueOf(interest)+ " sh");
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 211, 122, 23);
		Apply.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Total Repay");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// calculating total repay
				
				totalAmount = loan+interest;
				
				rpayAmountlbl.setText(""+String.valueOf(totalAmount)+ " sh");
				
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_2.setBounds(10, 271, 122, 23);
		Apply.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Apply");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_4.setBounds(271, 298, 89, 23);
		Apply.add(btnNewButton_4);
		
		// current date
		todaydate = ""+java.time.LocalDate.now();
		
		JButton btnNewButton = new JButton("Duration");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				setdate = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				LocalDate tday = LocalDate.parse(todaydate);
				LocalDate sday = LocalDate.parse(setdate);
				
				month_gap = ChronoUnit.MONTHS.between(tday, sday);
				durationlbl.setText(""+String.valueOf(month_gap)+ " months");
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(330, 154, 89, 23);
		Apply.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(139, 95, 141, 22);
		Apply.add(textField);
		textField.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(138, 151, 142, 21);
		Apply.add(dateChooser);
		
		grupnamelbl = new JLabel();
		grupnamelbl.setBounds(442, 44, 89, 14);
		Apply.add(grupnamelbl);
		
		JButton btnNewButton_5 = new JButton("Get Group");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getGroup();
				
				if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Equity")) {
					
					grupnamelbl.setText("Equity");
					namelbl.setText("Equity");
					sharelbl.setText(String.valueOf(equityTotalShares));
					
				}
				if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("KCB")) {
					
					grupnamelbl.setText("KCB");
					namelbl.setText("KCB");
					sharelbl.setText(String.valueOf(kcbTotalShares));
					
				}
				if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Murata")) {
					
					grupnamelbl.setText("Murata");
					namelbl.setText("Murata");
					sharelbl.setText(String.valueOf(murataTotalShares));
					
				}
				
			}
		});
		btnNewButton_5.setBounds(315, 40, 104, 23);
		Apply.add(btnNewButton_5);
		
		sharelbl = new JLabel();
		sharelbl.setBounds(139, 63, 122, 14);
		Apply.add(sharelbl);
		
		durationlbl = new JLabel();
		durationlbl.setBounds(450, 158, 92, 19);
		Apply.add(durationlbl);
		
		JButton applybtn = new JButton("Apply");
		applybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				shareAmount = Integer.valueOf(sharelbl.getText());
				
				if((loan <= shareAmount*3) && (month_gap <= 60)) {
					
					JOptionPane.showMessageDialog(null, "Your Group Loan Has Been Approved");
					createconnection();
				}
				if(loan > shareAmount*3) {
					
					JOptionPane.showMessageDialog(null, "Your Loan Exceed tripple Share Amount.\n Please try a lower amount.\n Thankyou");
				}
				if(month_gap > 60) {
					
					JOptionPane.showMessageDialog(null, "Your Repay Period Exceed 5 years.\n Thankyou");
				}
			}
		});
		applybtn.setBounds(442, 264, 89, 23);
		Apply.add(applybtn);
		
		InterestLabel = new JLabel();
		InterestLabel.setBounds(174, 211, 122, 23);
		Apply.add(InterestLabel);
		
		rpayAmountlbl = new JLabel();
		rpayAmountlbl.setBounds(174, 271, 122, 18);
		Apply.add(rpayAmountlbl);
		
		JPanel Repay = new JPanel();
		Repay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// getting group loan details
				
				getDetails();
			}
		});
		ApplytabbedPane.addTab("Loan Repay", null, Repay, null);
		Repay.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Group Name");
		lblNewLabel_1.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 86, 23);
		Repay.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Loan Amount");
		lblNewLabel_3.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 52, 79, 23);
		Repay.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Date Borrowed");
		lblNewLabel_7.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 94, 86, 20);
		Repay.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Today Date");
		lblNewLabel_8.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		lblNewLabel_8.setBounds(316, 94, 86, 20);
		Repay.add(lblNewLabel_8);
		
		JButton btnNewButton_6 = new JButton("Show Interest");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loanamount = Integer.parseInt(loanLbl.getText());
				
				String borrowdate = borrowdatelbl.getText();
				
				LocalDate tday = LocalDate.parse(todaydate);
				LocalDate bday = LocalDate.parse(borrowdate);
				
				repaymonth_gap = ChronoUnit.MONTHS.between(bday, tday); 
				
				payinterest = loanamount*groupInterestRate*repaymonth_gap;
				
				interestLbl.setText(""+String.valueOf(payinterest)+ "sh");
				
			}
		});
		btnNewButton_6.setBackground(new Color(128, 128, 128));
		btnNewButton_6.setForeground(new Color(0, 0, 128));
		btnNewButton_6.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		btnNewButton_6.setBounds(10, 160, 144, 23);
		Repay.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Total Repay Amount");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				repaytotal = payinterest+loanamount;
				
				repayamount.setText(""+String.valueOf(repaytotal)+ "sh");
			}
		});
		btnNewButton_7.setBackground(new Color(192, 192, 192));
		btnNewButton_7.setForeground(new Color(64, 0, 128));
		btnNewButton_7.setFont(new Font("Palatino Linotype", Font.BOLD, 12));
		btnNewButton_7.setBounds(10, 221, 163, 23);
		Repay.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Pay");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				payment = "paid";
				
				JOptionPane.showMessageDialog(null, "Your Group Loan Has Been Settled\n ThankYou");
				
				repayment();
			}
		});
		btnNewButton_8.setBackground(new Color(128, 128, 64));
		btnNewButton_8.setForeground(new Color(0, 0, 128));
		btnNewButton_8.setFont(new Font("Palatino Linotype", Font.BOLD, 11));
		btnNewButton_8.setBounds(248, 264, 128, 23);
		Repay.add(btnNewButton_8);
		
		dateLbl = new JLabel();
		dateLbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
		dateLbl.setText(""+ java.time.LocalDate.now());
		dateLbl.setBounds(433, 94, 91, 14);
		Repay.add(dateLbl);
		
		namelbl = new JLabel();
		namelbl.setBounds(145, 12, 101, 14);
		Repay.add(namelbl);
		
		loanLbl = new JLabel();
		loanLbl.setBounds(145, 53, 101, 14);
		Repay.add(loanLbl);
		
		borrowdatelbl = new JLabel();
		borrowdatelbl.setBounds(145, 94, 101, 14);
		Repay.add(borrowdatelbl);
		
		interestLbl = new JLabel();
		interestLbl.setBounds(220, 169, 156, 14);
		Repay.add(interestLbl);
		
		repayamount = new JLabel();
		repayamount.setBounds(220, 222, 156, 14);
		Repay.add(repayamount);
		
		JButton btnNewButton_3 = new JButton("HomePage");
		btnNewButton_3.addActionListener(new ActionListener() {
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
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_3.setBounds(10, 348, 89, 23);
		contentPane.add(btnNewButton_3);
	
	}
	
	static void getGroup() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String selectQuery = "SELECT sum(Jan+Feb+March+April+May+June+July+Aug+Sep+Oct+Nov) AS TotalShares FROM mwanzobaraka.equityshares";
			PreparedStatement ps = connection.prepareStatement(selectQuery);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				equityTotalShares = rs.getInt("TotalShares");
				
			}
			
			String selectQuery1 = "SELECT sum(Jan+Feb+March+April+May+June+July+Aug+Sep+Oct+Nov) AS TotalShares FROM mwanzobaraka.kcbshares";
			PreparedStatement ps1 = connection.prepareStatement(selectQuery);
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next()) {
				
				kcbTotalShares = rs1.getInt("TotalShares");
				
			}
			
			String selectQuery2 = "SELECT sum(Jan+Feb+March+April+May+June+July+Aug+Sep+Oct+Nov) AS TotalShares FROM mwanzobaraka.muratashares";
			PreparedStatement ps2 = connection.prepareStatement(selectQuery);
			ResultSet rs2 = ps2.executeQuery();
			
			if(rs2.next()) {
				
				murataTotalShares = rs2.getInt("TotalShares");
				
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void createconnection() {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO mwanzobaraka.grouploan(GroupName,GroupShares,GroupLoan,RepayAmount,BorrowDate,RepayDate)" + " VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, grupnamelbl.getText());
			ps.setInt(2, shareAmount);
			ps.setString(3, String.valueOf(loan));
			ps.setDouble(4, totalAmount);
			ps.setString(5, todaydate);
			ps.setString(6,setdate);
			
			ps.execute();
			connection.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void getDetails() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String getQuery = "SELECT * FROM mwanzobaraka.grouploan WHERE GroupName = ?";
			PreparedStatement ps3 = connection.prepareStatement(getQuery);
			ps3.setString(1, namelbl.getText());
			
			ResultSet rs = ps3.executeQuery();
			
			if(rs.next()) {
				
				loanLbl.setText(String.valueOf(rs.getInt("GroupLoan")));
				borrowdatelbl.setText(rs.getString("BorrowDate"));
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static void repayment() {
		
		String brd = borrowdatelbl.getText();
		String currentdate = dateLbl.getText();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO mwanzobaraka.grouploanrepay(GroupName,LoanAmount,RepayAmount,BorrowDate,RepayDate,Payment)"+"VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, namelbl.getText());
			ps.setInt(2, loanamount);
			ps.setDouble(3, repaytotal);
			ps.setString(4, brd);
			ps.setString(5, currentdate);
			ps.setString(6, payment);
			
			ps.execute();
			
			String query1 = "DELETE FROM mwanzobaraka.grouploan WHERE GroupName = ?";
			PreparedStatement ps1 = connection.prepareStatement(query1);
			ps1.setString(1, namelbl.getText());
			
			
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
