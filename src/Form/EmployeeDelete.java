package Form;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import Models.DBConnection;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmployeeDelete extends JInternalFrame {
	private JTextField tfEmployeeID;
	private JTextField tfEmployeeName;
	private JTextField tfMobileNo;
	private JTextField tfDOB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDelete frame = new EmployeeDelete();
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
	public EmployeeDelete() {
		setTitle("Employee Delete");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 679, 499);
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
		lblAddress.setBounds(47, 121, 54, 14);
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
		
		JButton btnUpdate = new JButton("Delete");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfEmployeeID.getText();
				String sql = "DELETE FROM USER_INFO WHERE userID = " + "'" + id + "'";
				try {
					PreparedStatement stmt = DBConnection.Connect().prepareStatement(sql);
					stmt.execute();
					JOptionPane.showMessageDialog(getContentPane(), "Delete Successful", "Delete", JOptionPane.WARNING_MESSAGE);
					DBConnection.Connect().close();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(156, 401, 89, 23);
		getContentPane().add(btnUpdate);
		
		
		JPanel pnlImage = new JPanel();
		pnlImage.setBounds(435, 216, 158, 203);
		getContentPane().add(pnlImage);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfEmployeeID.getText();
				String sql = "SELECT * FROM USER_INFO WHERE userID = " + "'" + id + "'";

				ResultSet rs = DBConnection.Query(sql);
				try {
					while (rs.next()) {
						tfEmployeeName.setText(rs.getString("name"));
						taAddress.setText(rs.getString("address"));
						String gd = rs.getString("sex");
						if (gd.equals("Male")) {
							rdbtnMale.setSelected(false);
							rdbtnFemale.setSelected(true);
						} else {
							rdbtnFemale.setSelected(false);
							rdbtnMale.setSelected(true);
						}
						tfDOB.setText(rs.getString("DOB"));
						String ms = rs.getString("martialStatus");
						if (ms.equals("Married")) {
							rdbtnMarried.setSelected(true);
							rdbtnUnmarried.setSelected(false);
						} else {
							rdbtnUnmarried.setSelected(true);
							rdbtnMarried.setSelected(false);
						}
						tfMobileNo.setText(rs.getString("mobileNo"));
						
					}
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(352, 31, 113, 23);
		getContentPane().add(btnSearch);
		
		tfDOB = new JTextField();
		tfDOB.setBounds(156, 271, 158, 20);
		getContentPane().add(tfDOB);
		tfDOB.setColumns(10);


	}
}
