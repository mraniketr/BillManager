package lOGIN_SYS;
import java.sql.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.awt.BorderLayout;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.*;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class BillManager extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;
	private JTextField txtamount;
	double sum=0.00;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillManager frame = new BillManager();
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
	public BillManager() {
		setResizable(false);
		setTitle("BILL MANAGER ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblGstSlab = new JLabel("GST SLAB");
		lblGstSlab.setBounds(8, 171, 66, 14);
		contentPane.add(lblGstSlab);
		
		String[] GSTs = { "0%", "5%", "12%", "18%", "28%" };
		JComboBox comboBox_1 = new JComboBox(GSTs);
		comboBox_1.setBounds(91, 171, 120, 20);
		contentPane.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 202, 120, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblItemName = new JLabel("ITEM NAME");
		lblItemName.setBounds(8, 202, 73, 14);
		contentPane.add(lblItemName);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setBounds(8, 241, 66, 14);
		contentPane.add(lblPrice);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(91, 238, 120, 20);
		contentPane.add(textField_2);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setBounds(8, 279, 66, 14);
		contentPane.add(lblQuantity);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 276, 120, 20);
		contentPane.add(textField_3);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(8, 58, 125, 14);
		contentPane.add(lblName);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(91, 55, 120, 20);
		contentPane.add(textField_4);
		
		JLabel lblMobileNumber = new JLabel("MOBILE");
		lblMobileNumber.setBounds(8, 84, 73, 14);
		contentPane.add(lblMobileNumber);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(91, 81, 120, 20);
		contentPane.add(textField_5);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(2, 130, 209, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(8, 309, 209, 2);
		contentPane.add(separator_1);
		
		JLabel lblDiscount = new JLabel("DISCOUNT");
		lblDiscount.setBounds(8, 339, 73, 14);
		contentPane.add(lblDiscount);
		
		textField_6 = new JTextField();
		textField_6.setBounds(94, 336, 73, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 365, 201, 2);
		contentPane.add(separator_2);
		
		JButton btnPrint = new JButton("SAVE");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document doc = new Document();
	            try {
					PdfWriter.getInstance(doc, new FileOutputStream("BILL.pdf"));
					doc.open();
					doc.add(new Paragraph ("= = = = = = = = = = = = = = = = = = = CUSTOMER BILL = = = = = = = = = = = = = = = = = = = = \nName :"+textField_4.getText()+"\nMobile No:"+textField_5.getText()+"\n\n\n"));
		            
					PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
		            //adding table headers
		            for (int i = 0; i < table.getColumnCount(); i++) {
		                pdfTable.addCell(table.getColumnName(i));
		            }
		            //extracting data from the JTable and inserting it to PdfPTable
		            for (int rows = 0; rows < table.getRowCount(); rows++) {
		                for (int cols = 0; cols < table.getColumnCount(); cols++) {
		                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());
		                }
		            }
		            doc.add(pdfTable);
					doc.add(new Paragraph ("\n\nTotal Amount:"+txtamount.getText()));
		            doc.close();
		            System.out.println("done");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPrint.setBounds(15, 412, 66, 23);
		contentPane.add(btnPrint);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Thank you for using our application.");
				System.exit(0);
			}
		});
		btnExit.setBounds(145, 412, 66, 23);
		contentPane.add(btnExit);
		
		DefaultTableModel model = new DefaultTableModel(); 
		table = new JTable(model);
		table.setForeground(SystemColor.desktop);
		table.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 13));
		table.setBackground(SystemColor.control);
		table.setRowSelectionAllowed(false);
		table.setBounds(223, 42, 433, 360);
		contentPane.add(table);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			double gst = 0.00, discount, price=0.00;
			int units;
			price=Float.parseFloat(textField_2.getText());
			discount=Float.parseFloat(textField_6.getText());
			units=Integer.parseInt(textField_3.getText());
			price=price*units;
			double sub;
			
			sub = price*discount*0.01;
				if(comboBox_1.getSelectedItem().equals("0%"))
					 gst=0;
				else if(comboBox_1.getSelectedItem().equals("5%"))
					 gst=price*(0.05);
				else if(comboBox_1.getSelectedItem().equals("12%"))
					 gst=price*(0.12);
				else if(comboBox_1.getSelectedItem().equals("18%"))
					gst=price*(0.18);
				else if(comboBox_1.getSelectedItem().equals("28%"))
					gst=price*(0.28);
				
				double pricefinal=(price+gst)-sub;
				model.addRow(new Object[]{textField_1.getText(), textField_3.getText(),comboBox_1.getSelectedItem(),textField_2.getText(),textField_6.getText(),pricefinal});
				sum=sum+pricefinal;
				txtamount.setText(Double.toString(sum));
				
				textField_1.setText(null);		// to make buttons empty after add click
				textField_2.setText(null);
				textField_3.setText(null);
				textField_6.setText(null);
	        }
		});
		btnNewButton.setBounds(20, 378, 176, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("%");
		label.setBounds(173, 338, 61, 16);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblNewLabel.setBounds(223, 26, 82, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblUnits = new JLabel("UNITS");
		lblUnits.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblUnits.setBounds(300, 26, 82, 16);
		contentPane.add(lblUnits);
		
		JLabel lblNewLabel_1 = new JLabel("TAX");
		lblNewLabel_1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblNewLabel_1.setBounds(381, 26, 82, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PRICE");
		lblNewLabel_2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblNewLabel_2.setBounds(438, 26, 82, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DISCOUNT");
		lblNewLabel_3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblNewLabel_3.setBounds(499, 26, 82, 16);
		contentPane.add(lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(564, 414, -86, 21);
		contentPane.add(textArea);
		
		txtamount = new JTextField();
		txtamount.setEditable(false);
		txtamount.setBounds(526, 409, 130, 26);
		contentPane.add(txtamount);
		txtamount.setColumns(10);
		
		JLabel lblItemTotal = new JLabel("I-TOTAL");
		lblItemTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
		lblItemTotal.setBounds(591, 26, 82, 16);
		contentPane.add(lblItemTotal);
		
		JLabel lblBillManager = new JLabel("BILL MANAGER 1.0");
		lblBillManager.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
		lblBillManager.setBounds(8, 11, 188, 36);
		contentPane.add(lblBillManager);
		
		JLabel lblNewLabel_4 = new JLabel("TOTAL AMOUNT");
		lblNewLabel_4.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
		lblNewLabel_4.setBounds(370, 401, 130, 40);
		contentPane.add(lblNewLabel_4);
		
		
		// Create a couple of columns 
		model.addColumn("NAME"); 
		model.addColumn("UNIT");
		model.addColumn("TAX"); 
		model.addColumn("PRICE"); 
		model.addColumn("DISCOUNT"); 
		model.addColumn("ITEMPRICE");

	}
}
