package Form;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Models.DBConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddDriver extends JInternalFrame {
	private JTextField tfDriverID;
	private JTextField tfDriverName;
	private JTextField tfDriverLicense;
	private String[] type = {"Motocycle", "Truck"};
	private JTextField tfVehicleSur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDriver frame = new AddDriver();
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
	public AddDriver() {
		setTitle("Add Driver");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblDriverID = new JLabel("Driver ID");
		lblDriverID.setBounds(29, 24, 63, 14);
		getContentPane().add(lblDriverID);
		
		tfDriverID = new JTextField();
		tfDriverID.setBounds(134, 21, 86, 20);
		getContentPane().add(tfDriverID);
		tfDriverID.setColumns(10);
		
		JLabel lblDriverName = new JLabel("Driver's Name");
		lblDriverName.setBounds(29, 59, 95, 14);
		getContentPane().add(lblDriverName);
		
		tfDriverName = new JTextField();
		tfDriverName.setBounds(134, 56, 157, 20);
		getContentPane().add(tfDriverName);
		tfDriverName.setColumns(10);
		
		JLabel lblDriverLicense = new JLabel("Driver's License No.");
		lblDriverLicense.setBounds(10, 95, 114, 14);
		getContentPane().add(lblDriverLicense);
		
		tfDriverLicense = new JTextField();
		tfDriverLicense.setBounds(134, 92, 157, 20);
		getContentPane().add(tfDriverLicense);
		tfDriverLicense.setColumns(10);
		
		JLabel lblDriverType = new JLabel("Driver Type");
		lblDriverType.setBounds(24, 132, 82, 14);
		getContentPane().add(lblDriverType);
		
		JComboBox cbDriverType = new JComboBox(type);
		cbDriverType.setBounds(134, 128, 132, 22);
		getContentPane().add(cbDriverType);
		
		JButton btnAddDriver = new JButton("Add Driver");
		btnAddDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfDriverID.getText();
				String name = tfDriverName.getText();
				String license = tfDriverLicense.getText();
				String vehicle = cbDriverType.getSelectedItem().toString();
				int surcharge = Integer.parseInt(tfVehicleSur.getText());
				String Query = "INSERT INTO DRIVER " + "VALUES(" + "'" + id + "'" + "," + "'" +name+ "'" + "," + "'" + license + "'" + "," + "'" + vehicle + "'" + "," + "'" + surcharge + "'" +  ")";
				
				try {
					DBConnection.Insert().executeUpdate(Query);
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
		btnAddDriver.setBounds(131, 219, 89, 23);
		getContentPane().add(btnAddDriver);
		
		JLabel lblVehicleCharge = new JLabel("Vehicle Surcharge");
		lblVehicleCharge.setBounds(24, 171, 110, 14);
		getContentPane().add(lblVehicleCharge);
		
		tfVehicleSur = new JTextField();
		tfVehicleSur.setBounds(144, 168, 195, 20);
		getContentPane().add(tfVehicleSur);
		tfVehicleSur.setColumns(10);
		
		

	}
}
