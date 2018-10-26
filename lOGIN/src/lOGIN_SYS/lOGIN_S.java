package lOGIN_SYS;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class lOGIN_S {

	JFrame frmBillManagerBeta;
	private JTextField txtUSERNAME;
	private JPasswordField txtPASSWORD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lOGIN_S window = new lOGIN_S();
					
					window.frmBillManagerBeta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public lOGIN_S() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBillManagerBeta = new JFrame();
		frmBillManagerBeta.getContentPane().setBackground(Color.ORANGE);
		frmBillManagerBeta.setBackground(Color.ORANGE);
		frmBillManagerBeta.setResizable(false);
		frmBillManagerBeta.setTitle("BILL MANAGER BETA ");
		
		frmBillManagerBeta.setBounds(200, 200, 500, 300);
		frmBillManagerBeta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBillManagerBeta.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN/SIGNUP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(195, 23, 109, 30);
		frmBillManagerBeta.getContentPane().add(lblNewLabel);
		
		JLabel lblUSERNAME = new JLabel("USERNAME");
		lblUSERNAME.setBounds(94, 94, 92, 14);
		frmBillManagerBeta.getContentPane().add(lblUSERNAME);
		
		JLabel lblPASSWORD = new JLabel("PASSWORD");
		lblPASSWORD.setBounds(94, 138, 77, 14);
		frmBillManagerBeta.getContentPane().add(lblPASSWORD);
		
		txtUSERNAME = new JTextField();
		txtUSERNAME.setBounds(198, 91, 175, 20);
		frmBillManagerBeta.getContentPane().add(txtUSERNAME);
		txtUSERNAME.setColumns(10);
		
		txtPASSWORD = new JPasswordField();
		txtPASSWORD.setBounds(198, 135, 175, 20);
		frmBillManagerBeta.getContentPane().add(txtPASSWORD);
		
		JButton btnLOGIN = new JButton("LOGIN");
		btnLOGIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","");
				Statement stmt =con.createStatement();
				String sql = "Select * from tblogin where username='"+txtUSERNAME.getText()+"'and password ='"+txtPASSWORD.getText()+"'";
				ResultSet rs =stmt.executeQuery(sql);
					if(rs.next())
						{
						JOptionPane.showMessageDialog(null,"Login success");
						BillManager link2 = new BillManager();   
						link2.setVisible(true); 
						}
					else
						JOptionPane.showMessageDialog(null,"incorrect details");
					con.close();
				} catch(Exception e1){System.out.println(e1);}
				}
				
		});
		btnLOGIN.setBounds(33, 204, 89, 23);
		frmBillManagerBeta.getContentPane().add(btnLOGIN);
		
		JButton btnRESET = new JButton("RESET");
		btnRESET.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUSERNAME.setText(null);
				txtPASSWORD.setText(null);
			}
		});
		btnRESET.setBounds(132, 204, 89, 23);
		frmBillManagerBeta.getContentPane().add(btnRESET);
		
		JButton btnEXIT = new JButton("EXIT");
		btnEXIT.setBackground(Color.RED);
		btnEXIT.addActionListener(new ActionListener() {
			 JButton frmLoginSystem;

			public void actionPerformed(ActionEvent e) {
				
				frmLoginSystem = new JButton("EXIT");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm to exit","login system",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				System.exit(0);	
				}
				
			}
		});
		btnEXIT.setBounds(369, 204, 89, 23);
		frmBillManagerBeta.getContentPane().add(btnEXIT);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 181, 464, 2);
		frmBillManagerBeta.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 64, 464, 2);
		frmBillManagerBeta.getContentPane().add(separator_1);
		
		JButton btnSIGNUP = new JButton("SIGNUP");
		btnSIGNUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SIGNUP link1 = new SIGNUP();   
				link1.setVisible(true); 
			}
		});
		btnSIGNUP.setBounds(231, 204, 89, 23);
		frmBillManagerBeta.getContentPane().add(btnSIGNUP);
	}
}
