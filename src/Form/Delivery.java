package Form;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Models.DBConnection;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Delivery extends JInternalFrame {
	private JTextField tfID;
	private JTextField tfProductName;
	private JTextField tfDeliveryDay;
	private JTextField tfCusName;
	private JTextField tfCusPhone;
	private JTextField tfPrice;
	private JTextField tfWeight;
	private JTextField tfHeight;
	private JTextField tfWidth;
	private String[] AreaID = null;
	private String[] DriverID = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delivery frame = new Delivery();
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
	public Delivery() {
		setTitle("Delivery Order");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 606, 634);
		getContentPane().setLayout(null);

		JLabel lblID = new JLabel("ID");
		lblID.setBounds(10, 28, 38, 14);
		getContentPane().add(lblID);

		tfID = new JTextField();
		tfID.setBounds(58, 25, 86, 20);
		getContentPane().add(tfID);
		tfID.setColumns(10);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setBounds(10, 73, 80, 14);
		getContentPane().add(lblProductName);

		tfProductName = new JTextField();
		tfProductName.setBounds(104, 70, 171, 20);
		getContentPane().add(tfProductName);
		tfProductName.setColumns(10);

		JLabel lblDeliveryDay = new JLabel("Delivery Day");
		lblDeliveryDay.setBounds(10, 116, 80, 14);
		getContentPane().add(lblDeliveryDay);

		tfDeliveryDay = new JTextField();
		tfDeliveryDay.setBounds(124, 113, 171, 20);
		getContentPane().add(tfDeliveryDay);
		tfDeliveryDay.setColumns(10);

		JLabel lblCusName = new JLabel("Customer's Name");
		lblCusName.setBounds(10, 160, 108, 14);
		getContentPane().add(lblCusName);

		tfCusName = new JTextField();
		tfCusName.setBounds(150, 157, 171, 20);
		getContentPane().add(tfCusName);
		tfCusName.setColumns(10);

		JLabel lblCusAddress = new JLabel("Customer's Address");
		lblCusAddress.setBounds(10, 199, 130, 14);
		getContentPane().add(lblCusAddress);

		JTextArea taCusAddress = new JTextArea();
		taCusAddress.setBounds(150, 194, 161, 73);
		getContentPane().add(taCusAddress);

		JLabel lblCusPhone = new JLabel("Customer's Phone");
		lblCusPhone.setBounds(10, 288, 116, 14);
		getContentPane().add(lblCusPhone);

		tfCusPhone = new JTextField();
		tfCusPhone.setBounds(136, 285, 139, 20);
		getContentPane().add(tfCusPhone);
		tfCusPhone.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 341, 46, 14);
		getContentPane().add(lblPrice);

		tfPrice = new JTextField();
		tfPrice.setBounds(76, 338, 146, 20);
		getContentPane().add(tfPrice);
		tfPrice.setColumns(10);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(10, 377, 56, 14);
		getContentPane().add(lblWeight);

		tfWeight = new JTextField();
		tfWeight.setBounds(76, 374, 146, 20);
		getContentPane().add(tfWeight);
		tfWeight.setColumns(10);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(10, 416, 56, 14);
		getContentPane().add(lblHeight);

		tfHeight = new JTextField();
		tfHeight.setColumns(10);
		tfHeight.setBounds(76, 413, 146, 20);
		getContentPane().add(tfHeight);

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(10, 454, 56, 14);
		getContentPane().add(lblWidth);

		tfWidth = new JTextField();
		tfWidth.setBounds(76, 451, 146, 20);
		getContentPane().add(tfWidth);
		tfWidth.setColumns(10);

		JLabel lblCOD = new JLabel("COD");
		lblCOD.setBounds(10, 496, 46, 14);
		getContentPane().add(lblCOD);

		JCheckBox chckbxCOD = new JCheckBox("");
		chckbxCOD.setBounds(76, 492, 97, 23);
		getContentPane().add(chckbxCOD);

		JLabel lblAreaID = new JLabel("Area ID");
		lblAreaID.setBounds(274, 341, 56, 14);
		getContentPane().add(lblAreaID);

		JComboBox<String> cbAreaID = new JComboBox();
		cbAreaID.setBounds(352, 337, 108, 22);
		getContentPane().add(cbAreaID);

		JLabel lblDriverID = new JLabel("Driver ID");
		lblDriverID.setBounds(274, 380, 56, 14);
		getContentPane().add(lblDriverID);

		JComboBox<String> cbDriverID = new JComboBox();
		cbDriverID.setBounds(352, 373, 108, 22);
		getContentPane().add(cbDriverID);
		
		JLabel lblNewLabel = new JLabel("MM-dd-yyyy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(305, 116, 63, 14);
		getContentPane().add(lblNewLabel);

		String query1 = "SELECT * FROM AREA";
		String query2 = "SELECT * FROM DRIVER";

		ResultSet rs = DBConnection.Query(query1);
		ResultSet rs2 = DBConnection.Query(query2);

		try {
			while (rs.next()) {
				cbAreaID.addItem(rs.getString("ID"));
			}
		} catch (SQLException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}

		try {
			while (rs2.next()) {
				cbDriverID.addItem(rs2.getString("ID"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfID.getText();
				String proName = tfProductName.getText();
				String deliDay = tfDeliveryDay.getText();
				String cusName = tfCusName.getText();
				String cusAddress = taCusAddress.getText();
				String cusPhone = tfCusPhone.getText();
				int price = Integer.parseInt(tfPrice.getText());
				int weight = Integer.parseInt(tfWeight.getText());
				int height = Integer.parseInt(tfHeight.getText());
				int width = Integer.parseInt(tfWidth.getText());
				String cod = null;
				if (chckbxCOD.isSelected()) {
					cod = "yes";
				} else {
					cod = "no";
				}

				String area = cbAreaID.getSelectedItem().toString();
				String driver = cbDriverID.getSelectedItem().toString();

				String sqlQuery = "INSERT INTO DELIVERY VALUES(" + "'" + id + "'" + "," + "'" + proName + "'" + "," + "'" + deliDay
						+ "'" + "," + "'" + cusName + "'" + "," + "'" + cusAddress + "'" + "," + "'" + cusPhone + "'"
						+ "," + price + "," + weight + "," + height + "," + width + "," + "'" + cod + "'" + "," + "'" + area + "'"
						+ "," + "'" + driver + "'" + ")";
				try {
					DBConnection.Insert().executeUpdate(sqlQuery);
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				try {
					DBConnection.Connect().close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}

			}
		});
		btnSave.setBounds(222, 532, 89, 23);
		getContentPane().add(btnSave);

	}
}
