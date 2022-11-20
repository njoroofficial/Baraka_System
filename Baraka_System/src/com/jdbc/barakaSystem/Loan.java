package com.jdbc.barakaSystem;

import java.awt.Dimension;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Loan extends JFrame {

	private JPanel contentPane;
	
	public static JLabel lDLabel,loanNameLbl,sharesLbl,repayIDLbl,repaynameLbl,durationlbl,ApplySharesLbl,rpayAmountlbl;
	
	private static JLabel currentloanAmountlbl;
	private static JLabel borrowdatelbl;
	private static JLabel repayTotallbl;
	private static JLabel todayDateLabel;
	private static JLabel rpaydatelabel;
	private static JLabel repayloanamount;
	private static JLabel repayborrowdate;
	private static JLabel repaycuurentdate;
	private static JLabel repayinterest;
	private static JLabel repayamount;
	
	private static JTextField loanAmountField;
	
	private static JPanel Repayment;
	
	private static JDateChooser dateChooser;
	
	private static Connection connection = null;
	
	private static String setdate,todaydate;
	
	public static String loanInfor;
	
	private static String id = Login.Identity;
	
	// member name
	private static String name = Login.name;
	private static String payment;
	private static String groupmemberid;
	
	private static Long month_gap,repaymonth_gap;
	private static int Loan;
	
	private static int loan,shares;
	private static float interest,totalAmount,repaytotal;
	private static float  indivInterestRate = (float) 0.012;
	private static float groupInterest = (float) 0.01;
	private static float  payinterest;
	

	/**
	 * Launch the application.
	 */
//	 public static void main(String[] args) {
//	
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Loan frame = new Loan();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
// }

	/**
	 * Create the frame.
	 */
	
	public Loan() {
		
		setTitle("Loan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(710, 376));
		setLocationRelativeTo(null);
//		setBounds(100, 100, 601, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane deatilsTabPane = new JTabbedPane(JTabbedPane.TOP);
		deatilsTabPane.setBounds(10, 11, 630, 276);
		contentPane.add(deatilsTabPane);
		
		JPanel Apply = new JPanel();
		deatilsTabPane.addTab("Apply Loan", null, Apply, null);
		Apply.setLayout(null);
		
		JLabel TotalsharesLabel = new JLabel("Shares");
		TotalsharesLabel.setFont(new Font("Algerian", Font.PLAIN, 12));
		TotalsharesLabel.setBounds(10, 11, 60, 14);
		Apply.add(TotalsharesLabel);
		
		JLabel loanTakenlabel = new JLabel("Loan_Amount");
		loanTakenlabel.setFont(new Font("Algerian", Font.PLAIN, 12));
		loanTakenlabel.setBounds(10, 42, 95, 14);
		Apply.add(loanTakenlabel);
		
		ApplySharesLbl = new JLabel();
		ApplySharesLbl.setText(String.valueOf(IndivContributions.totalShares)+ " sh");
		ApplySharesLbl.setBounds(122, 11, 95, 14);
		Apply.add(ApplySharesLbl);
		
		JLabel repaydatelbl = new JLabel("select repay date");
		repaydatelbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		repaydatelbl.setBounds(10, 78, 137, 14);
		Apply.add(repaydatelbl);
		
		loanAmountField = new JTextField();
		loanAmountField.setBounds(132, 38, 146, 20);
		Apply.add(loanAmountField);
		loanAmountField.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(142, 79, 166, 20);
		Apply.add(dateChooser);
		
		// current date
		todaydate = ""+java.time.LocalDate.now();
		
		JButton durationbtn = new JButton("Duration");
		durationbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setdate = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				LocalDate tday = LocalDate.parse(todaydate);
				LocalDate sday = LocalDate.parse(setdate);
				
				month_gap = ChronoUnit.MONTHS.between(tday, sday);
				durationlbl.setText(""+String.valueOf(month_gap)+ " months");
				
			}
		});
		durationbtn.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		durationbtn.setBounds(355, 74, 81, 23);
		Apply.add(durationbtn);
		
		durationlbl = new JLabel();
		durationlbl.setBounds(483, 78, 81, 14);
		Apply.add(durationlbl);
		
		JLabel interestlbl = new JLabel();
		interestlbl.setBounds(173, 129, 131, 23);
		Apply.add(interestlbl);
		
		JButton interestbtn = new JButton("Show Interest");
		interestbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// calculating interest
				//interest = principal(loan)*rate*period(months)
				//interestRate = 1.2;
				
				checkGroup();
				
				if( id.equals(groupmemberid) ) {
					
					loan = Integer.parseInt(loanAmountField.getText());
					
					interest = loan*groupInterest*month_gap;
					
					interestlbl.setText(""+String.valueOf(interest)+ " sh");
					
				} else {
					
					loan = Integer.parseInt(loanAmountField.getText());
					
					interest = loan*indivInterestRate*month_gap;
					
					interestlbl.setText(""+String.valueOf(interest)+ " sh");
					
					
				}

			}
		});
		interestbtn.setFont(new Font("Algerian", Font.PLAIN, 11));
		interestbtn.setBounds(10, 129, 122, 23);
		Apply.add(interestbtn);
		
		JButton totalrpaybtn = new JButton("Total Repay");
		totalrpaybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// calculating total repay
				
				totalAmount = loan+interest;
				
				rpayAmountlbl.setText(""+String.valueOf(totalAmount)+ " sh");
				
				//rpaydatelbl.setText(setdate);
				//repayTotallbl.setText(""+String.valueOf(totalAmount)+ " sh");
				//currentloanAmountlbl.setText(""+String.valueOf(loan)+ " sh");
				
			}
		});
		totalrpaybtn.setFont(new Font("Algerian", Font.PLAIN, 11));
		totalrpaybtn.setBounds(10, 178, 122, 23);
		Apply.add(totalrpaybtn);
		
		rpayAmountlbl = new JLabel();
		rpayAmountlbl.setBounds(173, 187, 105, 14);
		Apply.add(rpayAmountlbl);
		
		JButton applybtn = new JButton("Apply");
		applybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				shares = IndivContributions.totalShares;
				
				if(id.equals(groupmemberid) && (loan <= shares*4) &&(month_gap <= 60)) {
					
					JOptionPane.showMessageDialog(null,"Your Loan "+loan+ " sh Amount has been Approved");
					
					loanInfor = "Your Loan Amount Is " +loan+ "\n Due By " +setdate+ "\n ThankYou";
					
					createConnection();
					
				}
				if(id.equals(groupmemberid) && (loan > shares*4)) {
					
					JOptionPane.showMessageDialog(null, "Loan Amount exceed four times amount of your shares\n"+"Please borrow a low amount");
					
				}
				if(id.equals(groupmemberid)  && (month_gap > 60)) {
					
					JOptionPane.showMessageDialog(null,"Repayment Period exceeds 5 years");
					
				}
				if(id.equals(groupmemberid) && (loan > shares*4) &&(month_gap > 60)) {
					
					JOptionPane.showMessageDialog(null, "Application Error");
					
				}
				
				
				if((loan <= shares*3) && (month_gap <= 36)) {
					
					JOptionPane.showMessageDialog(null,"Your Loan "+loan+ " sh Amount has been Approved");
					
					loanInfor = "Your Loan Amount Is " +loan+ "\n Due By " +setdate+ "\n ThankYou";
					
					createConnection();
					
				}
				if(loan > shares*3) {
					
					JOptionPane.showMessageDialog(null, "Loan Amount exceed tripple amount of your shares\n"+"Please borrow a low amount");
					
				}
				if(month_gap > 36) {
					
					JOptionPane.showMessageDialog(null,"Repayment Period exceeds 3 years");
					
					
				}
				if((loan > shares*3) && (month_gap > 36)) {
					
					JOptionPane.showMessageDialog(null, "Application Error");
				}
				
			}
		});
		applybtn.setFont(new Font("Times New Roman", Font.BOLD, 11));
		applybtn.setBounds(253, 214, 131, 23);
		Apply.add(applybtn);
		
		JLabel todaydatelbl = new JLabel("Today Date");
		todaydatelbl.setFont(new Font("Algerian", Font.ITALIC, 11));
		todaydatelbl.setBounds(304, 10, 80, 14);
		Apply.add(todaydatelbl);
		
		todayDateLabel = new JLabel();
		todayDateLabel.setText(""+ java.time.LocalDate.now());
		todayDateLabel.setBounds(425, 10, 139, 14);
		Apply.add(todayDateLabel);
		
		JPanel Details = new JPanel();
		Details.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				getResult();
			}
		});
		deatilsTabPane.addTab("Details", null, Details, null);
		Details.setLayout(null);
		
		JLabel MembIDlabel = new JLabel("MembID");
		MembIDlabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		MembIDlabel.setBounds(10, 11, 67, 14);
		Details.add(MembIDlabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		nameLabel.setBounds(10, 41, 67, 14);
		Details.add(nameLabel);
		
		JLabel TotalSharesLabel = new JLabel("Total_Shares");
		TotalSharesLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		TotalSharesLabel.setBounds(10, 74, 102, 14);
		Details.add(TotalSharesLabel);
		
		JLabel loanTakenLabel = new JLabel("Current Loan Amount");
		loanTakenLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		loanTakenLabel.setBounds(10, 110, 142, 14);
		Details.add(loanTakenLabel);
		
		JLabel loanborrowdateLabel = new JLabel("Date Borrowed");
		loanborrowdateLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		loanborrowdateLabel.setBounds(10, 141, 102, 19);
		Details.add(loanborrowdateLabel);
		
		lDLabel = new JLabel();
		//		lDLabel.setText(Registration.MembIDField.getText());
		//lDLabel.setText(Login.Identity);
		lDLabel.setBounds(113, 11, 67, 14);
		Details.add(lDLabel);
		
		loanNameLbl = new JLabel();
		//		loanNameLbl.setText(""+Registration.fNameField.getText()+" "+Registration.lNameField.getText());
		//loanNameLbl.setText(Login.name);
		loanNameLbl.setBounds(113, 41, 202, 19);
		Details.add(loanNameLbl);
		
		sharesLbl = new JLabel();
		//sharesLbl.setText(String.valueOf(IndivContributions.totalShares)+ " sh");
		sharesLbl.setBounds(156, 73, 102, 14);
		Details.add(sharesLbl);
		
		borrowdatelbl = new JLabel();		
		borrowdatelbl.setBounds(131, 143, 127, 14);
		Details.add(borrowdatelbl);
		
		JLabel repaytotallbl = new JLabel("Total Repay Amount");
		repaytotallbl.setFont(new Font("Algerian", Font.PLAIN, 11));
		repaytotallbl.setBounds(10, 184, 127, 14);
		Details.add(repaytotallbl);
		
		repayTotallbl = new JLabel();
		repayTotallbl.setBounds(182, 183, 127, 14);
		Details.add(repayTotallbl);
		
		currentloanAmountlbl = new JLabel();
		currentloanAmountlbl.setBounds(188, 109, 127, 14);
		Details.add(currentloanAmountlbl);
		
		JLabel LoanDurationlbl = new JLabel("Due Date");
		LoanDurationlbl.setFont(new Font("Algerian", Font.PLAIN, 11));
		LoanDurationlbl.setBounds(316, 146, 82, 14);
		Details.add(LoanDurationlbl);
		
		
		rpaydatelabel = new JLabel();
		rpaydatelabel.setBounds(470, 146, 119, 14);
		Details.add(rpaydatelabel);
		
		Repayment = new JPanel();
		deatilsTabPane.addTab("Loan Repayment", null, Repayment, null);
		Repayment.setLayout(null);
		
		JLabel IDLabel = new JLabel("MembID");
		IDLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		IDLabel.setBounds(10, 11, 72, 14);
		Repayment.add(IDLabel);
		
		JLabel loanNameLoan = new JLabel("Name");
		loanNameLoan.setFont(new Font("Algerian", Font.PLAIN, 11));
		loanNameLoan.setBounds(10, 46, 72, 14);
		Repayment.add(loanNameLoan);
		
		JLabel takenLoanLabel = new JLabel("Loan_Amount");
		takenLoanLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		takenLoanLabel.setBounds(10, 75, 86, 14);
		Repayment.add(takenLoanLabel);
		
		repayIDLbl = new JLabel();
//		repayIDLbl.setText(Registration.MembIDField.getText());
		repayIDLbl.setText(Login.Identity);
		repayIDLbl.setBounds(92, 11, 65, 14);
		Repayment.add(repayIDLbl);
				
		repaynameLbl = new JLabel();
//		repaynameLbl.setText(""+Registration.fNameField.getText()+" "+Registration.lNameField.getText());
		repaynameLbl.setText(Login.name);
		repaynameLbl.setBounds(92, 40, 196, 20);
		Repayment.add(repaynameLbl);
				
		JLabel dblbl = new JLabel("Date Borrowed");
		dblbl.setFont(new Font("Algerian", Font.PLAIN, 11));
		dblbl.setBounds(10, 112, 101, 14);
		Repayment.add(dblbl);
				
		JLabel repayborrowdatelbl = new JLabel();
		repayborrowdatelbl.setBounds(166, 112, 122, 14);
		Repayment.add(repayborrowdatelbl);
				
		JLabel lblNewLabel = new JLabel("Today Date");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		lblNewLabel.setBounds(315, 112, 106, 14);
		Repayment.add(lblNewLabel);
		
		JButton repayinterestbtn = new JButton("Show Interest");
		repayinterestbtn.setFont(new Font("Algerian", Font.PLAIN, 11));
		repayinterestbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String borrowdate = repayborrowdate.getText(); 
						
				LocalDate tday = LocalDate.parse(todaydate);
				LocalDate bday = LocalDate.parse(borrowdate);
				
				repaymonth_gap = ChronoUnit.MONTHS.between(bday, tday); 
				
				Loan = Integer.parseInt(repayloanamount.getText());
				
				payinterest = Loan*indivInterestRate*repaymonth_gap;
				
				repayinterest.setText(""+String.valueOf(payinterest)+ "sh");
		
			}
		});
		repayinterestbtn.setBounds(10, 147, 133, 23);
		Repayment.add(repayinterestbtn);
		
		JButton repayamountbtn = new JButton("Total Repay Amount");
		repayamountbtn.setFont(new Font("Algerian", Font.PLAIN, 11));
		repayamountbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				repaytotal = payinterest+Loan;
				
				repayamount.setText(""+String.valueOf(repaytotal)+ "sh");
			}
		});
		repayamountbtn.setBounds(10, 196, 172, 23);
		Repayment.add(repayamountbtn);
		
		JButton paybtn = new JButton("Pay");
		paybtn.setFont(new Font("Algerian", Font.PLAIN, 11));
		paybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				payment = "paid";
				JOptionPane.showMessageDialog(null, "Your Loan Has Been Settled");
				loanInfor = "Your Currently Have No Loan Borrowed \n ThankYou";
				repayDate();
				
			}
		});
		paybtn.setBounds(452, 196, 128, 23);
		Repayment.add(paybtn);
		
		repayloanamount = new JLabel();
		repayloanamount.setBounds(129, 75, 133, 14);
		Repayment.add(repayloanamount);
		
		repayborrowdate = new JLabel();
		repayborrowdate.setBounds(121, 112, 141, 14);
		Repayment.add(repayborrowdate);
		
		repaycuurentdate = new JLabel();
		repaycuurentdate.setText(todaydate);
		repaycuurentdate.setBounds(431, 112, 122, 14);
		Repayment.add(repaycuurentdate);
		
		repayinterest = new JLabel();
		repayinterest.setBounds(166, 151, 122, 14);
		Repayment.add(repayinterest);
		
		repayamount = new JLabel();
		repayamount.setBounds(232, 205, 133, 14);
		Repayment.add(repayamount);
		
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
		btnNewButton.setBounds(20, 305, 89, 23);
		contentPane.add(btnNewButton);
	}
	
	static void createConnection() {

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "INSERT INTO mwanzobaraka.applyloan (MembID,SharesAmount,LoanAmount,Interest,RepayAmount,DateBorrowed,RepayDate)" + " VALUES(?,?,?,?,?,?,?)";
			
			// create the mysql insert preparedstatement
			
		    PreparedStatement ps = connection.prepareStatement(query);
		    ps.setString(1, id);
		    ps.setInt(2, shares);
		    ps.setInt(3, loan);
		    ps.setDouble(4, interest);
			ps.setDouble(5, totalAmount);
			ps.setString(6, todaydate);
		    ps.setString(7, setdate);
		    
		    ps.execute();
		    
		    String query1 = "INSERT INTO mwanzobaraka.loandetails (MembID,Name,Shares,LoanAmount,RepayAmount,DateBorrowed,RepayDate)"+"VALUES(?,?,?,?,?,?,?)";
		    PreparedStatement ps1 = connection.prepareStatement(query1);
		    ps1.setString(1, id);
		    ps1.setString(2, name);
		    ps1.setString(3, String.valueOf(shares));
		    ps1.setString(4, String.valueOf(loan));
			ps1.setString(5, String.valueOf(totalAmount));
			ps1.setString(6, todaydate);
		    ps1.setString(7, setdate);
		
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
	
	public static void getResult() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query2 = "SELECT * from mwanzobaraka.loandetails WHERE MembID=?";
			PreparedStatement ps2 = connection.prepareStatement(query2);
			ps2.setString(1, id);
			
			ResultSet rs = ps2.executeQuery();
			
			if(rs.next() == false) {
				
				JOptionPane.showMessageDialog(null, "No Loan Borrowed Yet\n"+"ThankYou");
			}
			else {
				
				lDLabel.setText(rs.getString("MembID"));
				loanNameLbl.setText(rs.getString("Name"));
				sharesLbl.setText(rs.getString("Shares"));
				currentloanAmountlbl.setText(rs.getString("LoanAmount"));
				repayloanamount.setText(rs.getString("LoanAmount"));
				borrowdatelbl.setText(rs.getString("DateBorrowed"));
				repayborrowdate.setText(rs.getString("DateBorrowed"));
				rpaydatelabel.setText(rs.getString("RepayDate"));
				repayTotallbl.setText(rs.getString("RepayAmount"));
				
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
	
	static void repayDate() {
		
		String brd = repayborrowdate.getText();
		String currentdate = repaycuurentdate.getText();
		int loanmoney = Integer.parseInt(repayloanamount.getText());

		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			 String query3 = "INSERT INTO mwanzobaraka.loanrepayment (MembID,Name,LoanAmount,Interest,RepayAmount,BorrowedDate,RepayDate,Payment)"+"VALUES(?,?,?,?,?,?,?,?)";
			 PreparedStatement ps3 = connection.prepareStatement(query3);
			 ps3.setString(1, id);
			 ps3.setString(2, name);
			 ps3.setInt(3, loanmoney);
			 ps3.setFloat(4, payinterest);
			 ps3.setDouble(5, repaytotal);
			 ps3.setString(6, brd);
			 ps3.setString(7, currentdate);
			 ps3.setString(8, payment);
			 
			 ps3.execute();
			 
			 String query4 = "DELETE FROM mwanzobaraka.loandetails WHERE MembID = ?";
			 PreparedStatement ps4 = connection.prepareStatement(query4);
			 ps4.setString(1, id);
			 
			 ps4.execute();
			 
			 String query5 = "DELETE FROM mwanzobaraka.applyloan WHERE MembID = ?";
			 PreparedStatement ps5 = connection.prepareStatement(query5);
			 ps5.setString(1, id);
			 
			 ps5.execute();
			 
			 connection.close();	
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void checkGroup() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query4 = "SELECT * from mwanzobaraka.groupmembers WHERE MembID=?";
			PreparedStatement ps5 = connection.prepareStatement(query4);
			ps5.setString(1, id);
			
			ResultSet rs = ps5.executeQuery();
			
			if(rs.next() == false) {			
			}
			else {
				
				groupmemberid = rs.getString("MembID");
				
			}
			
			ps5.execute();
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
