package Form;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import Models.DBConnection;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ClientAddClient extends JInternalFrame {
	private JTextField tfFactoryId;
	private JTextField tfFactoryName;
	private JTextField tfFactoryOwner;
	private JTextField tfContactNo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientAddClient frame = new ClientAddClient();
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
	public ClientAddClient() {
		setTitle("Factory Form");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 705, 634);
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfFactoryId.getText(); //**
				String name = tfFactoryName.getText();			//** take data from user input.
				String owner = tfFactoryOwner.getText();		//**
				String address = taAddress.getText();			//**
				String contact = tfContactNo.getText();			//**
				//Create sql Query
				String sqlQuery = "INSERT INTO CLIENT_INFO " + "VALUES(" + "'" + id + "'" + "," + "'" +name+ "'" + "," + "'" + owner +"'" + "," + "'" + address+ "'" + "," + "'" + contact+ "'" + ")";
				try {
					DBConnection.Insert().executeUpdate(sqlQuery);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DBConnection.Connect().close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}	
		});
		btnSave.setBounds(218, 495, 89, 23);
		getContentPane().add(btnSave);
		
		

	}
}
