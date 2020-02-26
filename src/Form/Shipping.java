package Form;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.mysql.jdbc.ResultSetMetaData;
import java.util.*;

import Models.DBConnection;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PrintJob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class Shipping extends JInternalFrame {
	private JTextField tfBuiltyNo;
	private JTextField tfProductName;
	private JTextField tfCusName;
	private JTextField tfCusPhone;
	private JTextField tfPrice;
	private JTextField tfFee;
	private JTextField tfTotal;
	private JTable table;
	private String[] column = { "ID", "Product Name", "Delivary day", "Name", "Address", "Phone", "Price", "COD",
			"AREA ID", "DRIVER ID" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shipping frame = new Shipping();
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
	public Shipping() {
		setTitle("Shipping");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1288, 634);
		getContentPane().setLayout(null);

		JLabel lblBuiltyNo = new JLabel("Builty No.");
		lblBuiltyNo.setBounds(29, 57, 57, 14);
		getContentPane().add(lblBuiltyNo);

		tfBuiltyNo = new JTextField();
		tfBuiltyNo.setBounds(96, 54, 86, 20);
		getContentPane().add(tfBuiltyNo);
		tfBuiltyNo.setColumns(10);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(29, 109, 86, 14);
		getContentPane().add(lblProductName);

		tfProductName = new JTextField();
		tfProductName.setEditable(false);
		tfProductName.setBounds(125, 106, 287, 20);
		getContentPane().add(tfProductName);
		tfProductName.setColumns(10);

		JLabel lblCusName = new JLabel("Customer Name");
		lblCusName.setBounds(29, 163, 98, 14);
		getContentPane().add(lblCusName);

		tfCusName = new JTextField();
		tfCusName.setEditable(false);
		tfCusName.setBounds(125, 160, 287, 20);
		getContentPane().add(tfCusName);
		tfCusName.setColumns(10);

		JLabel lblCusAddress = new JLabel("Customer Address");
		lblCusAddress.setBounds(29, 208, 110, 14);
		getContentPane().add(lblCusAddress);

		JTextArea taCusAddress = new JTextArea();
		taCusAddress.setEditable(false);
		taCusAddress.setBounds(165, 203, 247, 83);
		getContentPane().add(taCusAddress);

		JLabel lblCusPhone = new JLabel("Customer Phone");
		lblCusPhone.setBounds(29, 321, 98, 14);
		getContentPane().add(lblCusPhone);

		tfCusPhone = new JTextField();
		tfCusPhone.setEditable(false);
		tfCusPhone.setBounds(165, 318, 165, 20);
		getContentPane().add(tfCusPhone);
		tfCusPhone.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(29, 368, 46, 14);
		getContentPane().add(lblPrice);

		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setBounds(96, 365, 208, 20);
		getContentPane().add(tfPrice);
		tfPrice.setColumns(10);

		JLabel lblFee = new JLabel("Transport Fee");
		lblFee.setBounds(29, 430, 98, 14);
		getContentPane().add(lblFee);

		tfFee = new JTextField();
		tfFee.setEditable(false);
		tfFee.setBounds(137, 427, 208, 20);
		getContentPane().add(tfFee);
		tfFee.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(65, 509, 86, 37);
		getContentPane().add(lblTotal);

		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setBounds(165, 509, 180, 30);
		getContentPane().add(tfTotal);
		tfTotal.setColumns(10);

		table = new JTable();
		table.setBounds(430, 583, 820, -560);
		getContentPane().add(table);
		try {
			ResultSet rs = DBConnection.Query(
					"SELECT ID,Product_Name,Delivery_Day,Customer_Name,Customer_Address,Customer_Phone,Price,COD,Area_ID,Driver_ID FROM DELIVERY");

			if (rs.next()) {

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob print = PrinterJob.getPrinterJob();
				print.setJobName("Print Record");
				print.setPrintable(new Printable() {
					
					@Override
					public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
						// TODO Auto-generated method stub
						if(pageIndex > 0)
						{
							return Printable.NO_SUCH_PAGE;
						}
						
						Graphics2D graphics2d = (Graphics2D)graphics;
						graphics2d.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
						graphics2d.scale(0.5, 0.5);
						getContentPane().paint(graphics2d);
						
						return Printable.PAGE_EXISTS;
					}
					boolean returningResult = print.printDialog();
				});
			}
		});
		btnPrint.setBounds(232, 558, 89, 23);
		getContentPane().add(btnPrint);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner sc = new Scanner(System.in);
				String id = tfBuiltyNo.getText();
				String sql = "SELECT * FROM DELIVERY INNER JOIN AREA ON Area_ID = AREA.ID INNER JOIN DRIVER ON Driver_ID = DRIVER.ID where DELIVERY.ID = "
						+ "'" + id + "'";

				ResultSet rs = DBConnection.Query(sql);
				try {
					while (rs.next()) {
						tfProductName.setText(rs.getString("Product_Name"));
						tfCusName.setText(rs.getString("Customer_Name"));
						taCusAddress.setText(rs.getString("Customer_Address"));
						tfCusPhone.setText(rs.getString("Customer_Phone"));
						tfPrice.setText(rs.getString("Price"));
						int price = Integer.parseInt(rs.getString("Price"));
						int transSurcharge = Integer.parseInt(rs.getString("Transport_Surcharge"));
						int vehicleSurcharge = Integer.parseInt(rs.getString("Vehicle_Surcharge"));
						int weight = Integer.parseInt(rs.getString("Weight"));
						int height = Integer.parseInt(rs.getString("Height"));
						int width = Integer.parseInt(rs.getString("Width"));
						int transportFee = transSurcharge + vehicleSurcharge;
						if(weight > 5)
						{
							transportFee += 10000;
						}
						if(height > 10) 
						{
							transportFee += 20000;
						}
						if(width > 10)
						{
							transportFee += 20000;
						}
						if(rs.getString("COD").equals("yes")) {
							transportFee += 3000;
						}
						
						tfFee.setText(String.valueOf(transportFee));
						
						int total = price + transportFee;
						tfTotal.setText(String.valueOf(total));
						
					}
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				try {
					DBConnection.Connect().close();
				} catch (SQLException e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}

			}
		});
		btnSearch.setBounds(215, 53, 89, 23);
		getContentPane().add(btnSearch);

	}
}
