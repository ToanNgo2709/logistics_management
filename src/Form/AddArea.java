package Form;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import Models.DBConnection;;

public class AddArea extends JInternalFrame {
	private JTextField tfAreaID;
	private JTextField tfAreaName;
	private JTextField tfSurcharge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddArea frame = new AddArea();
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
	public AddArea() {
		setTitle("Add Area");
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblArea = new JLabel("Area ID");
		lblArea.setBounds(53, 11, 46, 14);
		getContentPane().add(lblArea);
		
		tfAreaID = new JTextField();
		tfAreaID.setBounds(121, 8, 173, 20);
		getContentPane().add(tfAreaID);
		tfAreaID.setColumns(10);
		
		JButton btnAddArea = new JButton("Add Area");
		btnAddArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfAreaID.getText();
				String name = tfAreaName.getText();
				int Surcharge = Integer.parseInt(tfSurcharge.getText());
				String Query = "INSERT INTO AREA " + "VALUES(" + "'" + id + "'" + "," + "'" +name+ "'" + ","  + Surcharge + ")";
				
				try {
					DBConnection.Insert().executeUpdate(Query);
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				try {
					DBConnection.Connect().close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnAddArea.setBounds(154, 148, 89, 23);
		getContentPane().add(btnAddArea);
		
		JLabel lblAreaName = new JLabel("Area Name");
		lblAreaName.setBounds(42, 52, 70, 14);
		getContentPane().add(lblAreaName);
		
		tfAreaName = new JTextField();
		tfAreaName.setBounds(121, 49, 173, 20);
		getContentPane().add(tfAreaName);
		tfAreaName.setColumns(10);
		
		JLabel lblSurcharge = new JLabel("Surcharge");
		lblSurcharge.setBounds(42, 96, 70, 14);
		getContentPane().add(lblSurcharge);
		
		tfSurcharge = new JTextField();
		tfSurcharge.setBounds(145, 93, 173, 20);
		getContentPane().add(tfSurcharge);
		tfSurcharge.setColumns(10);
		
		
	}
}
