package lOGIN_SYS;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SIGNUP extends JFrame {

	private JPanel contentPane;
	private JTextField txtUSERNAME1;
	private JPasswordField txtPASSWORD1;
	private JPasswordField txtPASSWORD2;
	private JLabel lblNewLabel;
	private JLabel lblOrganisationName;
	private JTextField txtORG;
	private JLabel lblGstn;
	private JTextField txtGSTN;
	private JButton btnSIGNUP2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SIGNUP frame = new SIGNUP();
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
	public SIGNUP() {
		setResizable(false);
		setTitle("BILL MANAGER BETA ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setBounds(20, 93, 156, 14);
		contentPane.add(lblUsername);
		
		txtUSERNAME1 = new JTextField();
		txtUSERNAME1.setColumns(10);
		txtUSERNAME1.setBounds(171, 90, 175, 20);
		contentPane.add(txtUSERNAME1);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(20, 148, 156, 14);
		contentPane.add(lblPassword);
		
		txtPASSWORD1 = new JPasswordField();
		txtPASSWORD1.setBounds(171, 145, 175, 20);
		contentPane.add(txtPASSWORD1);
		
		JLabel lblRetypePassword = new JLabel("RETYPE PASSWORD:");
		lblRetypePassword.setBounds(20, 176, 156, 14);
		contentPane.add(lblRetypePassword);
		
		txtPASSWORD2 = new JPasswordField();
		txtPASSWORD2.setBounds(171, 173, 175, 20);
		contentPane.add(txtPASSWORD2);
		
		lblNewLabel = new JLabel("SIGN UP FORM");
		lblNewLabel.setBounds(159, 32, 129, 14);
		contentPane.add(lblNewLabel);
		
		lblOrganisationName = new JLabel("ORGANISATION NAME:");
		lblOrganisationName.setBounds(20, 119, 139, 14);
		contentPane.add(lblOrganisationName);
		
		txtORG = new JTextField();
		txtORG.setColumns(10);
		txtORG.setBounds(171, 119, 175, 20);
		contentPane.add(txtORG);
		
		lblGstn = new JLabel("GSTN");
		lblGstn.setBounds(20, 203, 139, 14);
		contentPane.add(lblGstn);
		
		txtGSTN = new JTextField();
		txtGSTN.setColumns(10);
		txtGSTN.setBounds(171, 200, 175, 20);
		contentPane.add(txtGSTN);
		
		btnSIGNUP2 = new JButton("SIGNUP");
		btnSIGNUP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtPASSWORD1.getText().equals(txtPASSWORD2.getText()))
				{
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
				}
				else if(!(txtPASSWORD1.getText().length()!=3))
				{
					JOptionPane.showMessageDialog(null, "Passwords is too short!");
				}
				else if(!(txtGSTN.getText().length()!=14))
				{
					JOptionPane.showMessageDialog(null, "GSTN Number is Invalid!");
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","");
						Statement stmt =con.createStatement();
						String signupquery = "INSERT INTO `tblogin`(`username`, `password`,`GSTN`,`ORGAN`) VALUES ('"+txtUSERNAME1.getText()+"','"+txtPASSWORD1.getText()+"','"+txtGSTN.getText()+"','"+txtORG.getText()+"')";
						stmt.executeUpdate(signupquery);
							con.close();
						} catch(Exception e1){System.out.println(e1);}
					JOptionPane.showMessageDialog(null, "Restart the application to login.");
					System.exit(0);
				}
			}
	
		});
		btnSIGNUP2.setBounds(187, 232, 89, 23);
		contentPane.add(btnSIGNUP2);
	}
}
