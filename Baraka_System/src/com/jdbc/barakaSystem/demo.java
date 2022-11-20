package com.jdbc.barakaSystem;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class demo extends JFrame {

	private JPanel contentPane;
	
	private static Connection connection = null;
	private static JTable table;
	
	private static String ID,Jan,Feb,March,April,May,June;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo frame = new demo();
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
	public demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int selectedRowIndex = table.getSelectedRow();
				ID = model.getValueAt(selectedRowIndex, 0).toString();
				Jan = model.getValueAt(selectedRowIndex, 1).toString();
				Feb = model.getValueAt(selectedRowIndex, 2).toString();
				March = model.getValueAt(selectedRowIndex, 3).toString();
				April = model.getValueAt(selectedRowIndex, 4).toString();
				May = model.getValueAt(selectedRowIndex, 5).toString();
				//June = model.getValueAt(selectedRowIndex, 6).toString();
				
				if(model.getValueAt(selectedRowIndex, 6).equals("")) {
					June = "moses";
				}
				
				System.out.println(ID);
				System.out.println(Jan);
				System.out.println(Feb);
				System.out.println(March);
				System.out.println(April);
				System.out.println(May);
				System.out.println(June);
				
			}
		});
		scrollPane.setBounds(10, 45, 1075, 158);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Show Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				connection();
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 235, 112, 38);
		contentPane.add(btnNewButton);
	}
	
	static void connection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/mwanzobaraka";
			String user = "root";
			String password = "12960";
			
			connection = DriverManager.getConnection(url, user, password);
			
			String query = "SELECT * FROM mwanzobaraka.individualshares";
			
			PreparedStatement ps = connection.prepareStatement(query);
			
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
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
