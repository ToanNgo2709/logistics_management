package Form;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Models.DBConnection;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.*;

public class ClientSearchUpdate extends JInternalFrame {
	private JTextField tfFactoryId;
	private JTextField tfFactoryName;
	private JTextField tfFactoryOwner;
	private JTextField tfContactNo;
	Scanner sc = new Scanner(System.in);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSearchUpdate frame = new ClientSearchUpdate();
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
	public ClientSearchUpdate() {
		setTitle("Client Search Update");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 724, 800);
		getContentPane().setLayout(null);

		JLabel lblFactoryId = new JLabel("Factory ID");
		lblFactoryId.setBounds(128, 83, 80, 14);
		getContentPane().add(lblFactoryId);

		tfFactoryId = new JTextField();
		tfFactoryId.setBounds(218, 80, 229, 20);
		getContentPane().add(tfFactoryId);
		tfFactoryId.setColumns(10);

		JLabel lblFactoryName = new JLabel("Factory Name");
		lblFactoryName.setBounds(128, 133, 80, 14);
		getContentPane().add(lblFactoryName);

		tfFactoryName = new JTextField();
		tfFactoryName.setBounds(219, 130, 228, 20);
		getContentPane().add(tfFactoryName);
		tfFactoryName.setColumns(10);

		JLabel lblFactoryOwner = new JLabel("Factory Owner");
		lblFactoryOwner.setBounds(128, 204, 80, 14);
		getContentPane().add(lblFactoryOwner);

		tfFactoryOwner = new JTextField();
		tfFactoryOwner.setBounds(218, 201, 229, 20);
		getContentPane().add(tfFactoryOwner);
		tfFactoryOwner.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(128, 284, 61, 14);
		getContentPane().add(lblAddress);

		JTextArea taAddress = new JTextArea();
		taAddress.setBounds(218, 279, 229, 111);
		getContentPane().add(taAddress);

		JLabel lblContactNo = new JLabel("Contact No.");
		lblContactNo.setBounds(128, 436, 69, 14);
		getContentPane().add(lblContactNo);

		tfContactNo = new JTextField();
		tfContactNo.setBounds(218, 433, 229, 20);
		getContentPane().add(tfContactNo);
		tfContactNo.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfFactoryId.getText(); // **
				String name = tfFactoryName.getText(); // ** take data from user input.
				String owner = tfFactoryOwner.getText(); // **
				String address = taAddress.getText(); // **
				String contact = tfContactNo.getText(); // **

				String sql = "UPDATE CLIENT_INFO SET factoryName = ?,factoryOwner = ?,factoryAddress = ?,contactNo = ? WHERE factoryID = ?";
				try {
					PreparedStatement stmt = DBConnection.Connect().prepareStatement(sql);
					stmt.setString(1, name);
					stmt.setString(2, owner);
					stmt.setString(3, address);
					stmt.setString(4, contact);
					stmt.setString(5, id);
					
					stmt.executeUpdate();
					DBConnection.Connect().close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(218, 495, 89, 23);
		getContentPane().add(btnUpdate);

		JButton btnSearchID = new JButton("Search");
		btnSearchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfFactoryId.getText();
				String sql = "SELECT * FROM CLIENT_INFO WHERE factoryID = " + "'" + id + "'";
				ResultSet rs = DBConnection.Query(sql);
				try {
					while (rs.next()) {
						tfFactoryName.setText(rs.getString("factoryName"));
						tfFactoryOwner.setText(rs.getString("factoryOwner"));
						taAddress.setText(rs.getString("factoryAddress"));
						tfContactNo.setText(rs.getString("contactNo"));
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
		btnSearchID.setBounds(496, 79, 89, 23);
		getContentPane().add(btnSearchID);

		JButton btnSearchName = new JButton("Search");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfFactoryName.getText();
				String sql = "SELECT * FROM CLIENT_INFO WHERE factoryName LIKE " + "'" + name + "'";
				ResultSet rs = DBConnection.Query(sql);
				try {
					while (rs.next()) {
						tfFactoryId.setText(rs.getString("factoryID"));
						tfFactoryOwner.setText(rs.getString("factoryOwner"));
						taAddress.setText(rs.getString("factoryAddress"));
						tfContactNo.setText(rs.getString("contactNo"));
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
		btnSearchName.setBounds(496, 129, 89, 23);
		getContentPane().add(btnSearchName);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(358, 495, 89, 23);
		getContentPane().add(btnPrint);

		JPanel panel = new JPanel();
		panel.setBounds(49, 544, 628, 164);
		getContentPane().add(panel);

	}
}
