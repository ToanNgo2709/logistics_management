package Form;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.*;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Models.DBConnection;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.time.chrono.JapaneseDate;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EmployeeAdd extends JInternalFrame {
	private JTextField tfEmployeeID;
	private JTextField tfEmployeeName;
	private JTextField tfMobileNo;
	private static String[] months = { "Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	private static int days[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
			25, 26, 27, 28, 29, 30, 31 };
	private JTextField tfUsername;
	private JTextField tfPassword;
	private String[] type = { "Employee", "Admin" };
	private JTextField tfDOB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeAdd frame = new EmployeeAdd();
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
	public EmployeeAdd() {
		setTitle("Employee Detail");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 700, 499);
		getContentPane().setLayout(null);

		JLabel lblEmployeeID = new JLabel("Employee ID");
		lblEmployeeID.setBounds(47, 35, 70, 14);
		getContentPane().add(lblEmployeeID);

		tfEmployeeID = new JTextField();
		tfEmployeeID.setBounds(156, 32, 138, 20);
		getContentPane().add(tfEmployeeID);
		tfEmployeeID.setColumns(10);

		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(47, 74, 99, 14);
		getContentPane().add(lblEmployeeName);

		tfEmployeeName = new JTextField();
		tfEmployeeName.setBounds(156, 71, 138, 20);
		getContentPane().add(tfEmployeeName);
		tfEmployeeName.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(47, 121, 70, 14);
		getContentPane().add(lblAddress);

		JTextArea taAddress = new JTextArea();
		taAddress.setBounds(156, 116, 138, 100);
		getContentPane().add(taAddress);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(47, 238, 46, 14);
		getContentPane().add(lblSex);

		// make a button group to make only select one radio button
		ButtonGroup Sex = new ButtonGroup();

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(156, 234, 54, 23);
		getContentPane().add(rdbtnMale);
		Sex.add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(233, 234, 81, 23);
		getContentPane().add(rdbtnFemale);
		Sex.add(rdbtnFemale);

		JLabel lblDOB = new JLabel("DOB");
		lblDOB.setBounds(47, 274, 31, 14);
		getContentPane().add(lblDOB);

		JLabel lblMaritalStatus = new JLabel("Marital Status");
		lblMaritalStatus.setBounds(47, 319, 99, 14);
		getContentPane().add(lblMaritalStatus);

		ButtonGroup ms = new ButtonGroup();

		JRadioButton rdbtnMarried = new JRadioButton("Married");
		rdbtnMarried.setBounds(156, 315, 70, 23);
		getContentPane().add(rdbtnMarried);
		ms.add(rdbtnMarried);

		JRadioButton rdbtnUnmarried = new JRadioButton("Unmarried");
		rdbtnUnmarried.setBounds(226, 315, 88, 23);
		getContentPane().add(rdbtnUnmarried);
		ms.add(rdbtnUnmarried);

		JLabel lblMobileNo = new JLabel("Mobile No.");
		lblMobileNo.setBounds(47, 356, 70, 14);
		getContentPane().add(lblMobileNo);

		tfMobileNo = new JTextField();
		tfMobileNo.setBounds(156, 353, 158, 20);
		getContentPane().add(tfMobileNo);
		tfMobileNo.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(337, 121, 46, 14);
		getContentPane().add(lblType);

		JComboBox cbType = new JComboBox(type);
		cbType.setBounds(435, 117, 158, 22);
		getContentPane().add(cbType);
		
		tfDOB = new JTextField();
		tfDOB.setBounds(156, 271, 138, 20);
		getContentPane().add(tfDOB);
		tfDOB.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfEmployeeID.getText();
				String userName = tfUsername.getText();
				String userPW = tfPassword.getText();
				String name = tfEmployeeName.getText();
				String address = taAddress.getText();
				String sex = Sex.getSelection().getActionCommand();// use to store radio button value into database
				rdbtnFemale.setActionCommand("Female");
				rdbtnMale.setActionCommand("Male");
				if (rdbtnFemale.isSelected()) {
					sex = "Female";
				} else if (rdbtnMale.isSelected()) {
					sex = "Male";
				}
				String DOB = tfDOB.getText();
				String martial = ms.getSelection().getActionCommand();
				rdbtnMarried.setActionCommand("Married");
				rdbtnUnmarried.setActionCommand("Unmarried");
				if (rdbtnMarried.isSelected()) {
					martial = "Married";
				} else if (rdbtnUnmarried.isSelected()) {
					martial = "Unmarried";
				}
				String mobile = tfMobileNo.getText();
				String type = cbType.getSelectedItem().toString();

				String Query = "INSERT INTO USER_INFO VALUES(" + "'" + id + "'" + "," + "'" + userName + "'" + "," + "'"
						+ userPW + "'" + "," + "'" + name + "'" + "," + "'" + address + "'" + "," + "'" + sex + "'"
						+ "," + "'" + DOB + "'" + "," + "'" + martial + "'" + "," + "'" + mobile + "'" + "," + "'" +type + "'"+ ")";
				
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
		btnSave.setBounds(156, 401, 89, 23);
		getContentPane().add(btnSave);

		JLabel lblImageInfo = new JLabel("");
		lblImageInfo.setBounds(435, 430, 158, 14);
		getContentPane().add(lblImageInfo);

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(337, 35, 70, 14);
		getContentPane().add(lblUserName);

		tfUsername = new JTextField();
		tfUsername.setBounds(435, 32, 198, 20);
		getContentPane().add(tfUsername);
		tfUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(337, 74, 70, 14);
		getContentPane().add(lblPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(435, 71, 198, 20);
		getContentPane().add(tfPassword);
		tfPassword.setColumns(10);

		
		
		JLabel lblNewLabel = new JLabel("MM-dd-yyyy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setBounds(303, 274, 46, 14);
		getContentPane().add(lblNewLabel);

	}
}
