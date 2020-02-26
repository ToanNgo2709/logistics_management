package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.DBConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/Multimedia/icons8-truck-16.png")));
		setTitle("Welcome to Logistis Management Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(46, 52, 73, 14);
		contentPane.add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setBounds(143, 49, 162, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(46, 101, 73, 14);
		contentPane.add(lblPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(143, 98, 162, 20);
		contentPane.add(pfPassword);

		// ** LOGIN WORKING
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = tfUsername.getText();
				String pw = pfPassword.getText();
				String sql = "SELECT * FROM USER_INFO";
				ResultSet rs = DBConnection.Query(sql);
				try {
					while (rs.next()) {
						String dbUser = rs.getString("userName");
						String dbPW = rs.getString("userPW");
						String type = rs.getString("Type");
						if (user.equals(dbUser) && pw.equals(dbPW)) {
							if(type.equals("Admin"))
							{
								mainForm main = new mainForm();
								main.setVisible(true);
								JOptionPane.showMessageDialog(main, "Welcome " + user, "Welcome to our Application",
										JOptionPane.INFORMATION_MESSAGE);
							} else if(type.equals("Employee"))
							{
								mainForm main = new mainForm();
								JOptionPane.showMessageDialog(main, "Welcome " + user, "Welcome to our Application",
										JOptionPane.INFORMATION_MESSAGE);
							}
							
						} else {
							JOptionPane.showMessageDialog(contentPane, "Invalid username or password",
									"Please re-enter information", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (SQLException e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
		});
		
				
							
		
		btnLogin.setBounds(216, 167, 89, 23);
		contentPane.add(btnLogin);

		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setBounds(143, 125, 144, 23);
		contentPane.add(chckbxRememberMe);

		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					pfPassword.setEchoChar((char) 0);
				} else {
					pfPassword.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setBounds(311, 97, 144, 23);
		contentPane.add(chckbxShowPassword);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(LoginForm.class
				.getResource("/Multimedia/FAVPNG_logistics-multimodal-transport-cargo-supply-chain_U8SZ9Sc6.png")));
		lblNewLabel.setBounds(10, 11, 500, 273);
		contentPane.add(lblNewLabel);
	}

}
