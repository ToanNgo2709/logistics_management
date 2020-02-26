package Form;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.DBConnection;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class mainForm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginForm login = new LoginForm();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainForm frame = new mainForm();
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
	public mainForm() {
		setForeground(Color.BLUE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainForm.class.getResource("/Multimedia/icons8-truck-16.png")));
		//set size for JFrame.
		setTitle("Logistics Management");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int ySize = ((int) tk.getScreenSize().height);
		int xSize = ((int) tk.getScreenSize().width);
		setSize(xSize, ySize);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);
		
		//Init Employee menu
		JMenu mnEmployee = new JMenu("Employee");
		mainMenuBar.add(mnEmployee);
		
		JMenuItem mnItemEmployeeAdd = new JMenuItem("Add");
		mnItemEmployeeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeAdd employeeAddFrame = new EmployeeAdd();
				employeeAddFrame.setVisible(true);
				contentPane.add(employeeAddFrame);
				
			}
		});
		mnEmployee.add(mnItemEmployeeAdd);
		
		JMenuItem mnItemEmployeeSeachUpdate = new JMenuItem("Search/Update");
		mnItemEmployeeSeachUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeSearchUpdate employeeSearchUpdateFrame = new EmployeeSearchUpdate();
				employeeSearchUpdateFrame.setVisible(true);
				contentPane.add(employeeSearchUpdateFrame);
			}
		});
		mnEmployee.add(mnItemEmployeeSeachUpdate);
		
		JMenuItem mnItemEmployeeDelete = new JMenuItem("Delete");
		mnItemEmployeeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDelete employeeDeleteFrame = new EmployeeDelete();
				employeeDeleteFrame.setVisible(true);
				contentPane.add(employeeDeleteFrame);
			}
		});
		mnEmployee.add(mnItemEmployeeDelete);
		
		//Init New menu;
		JMenu mnNew = new JMenu("New");
		mainMenuBar.add(mnNew);
		
		JMenuItem mnItemNewGoodsReciept = new JMenuItem("Delivery");
		mnItemNewGoodsReciept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delivery goodRecieptFrame = new Delivery();
				goodRecieptFrame.setVisible(true);
				contentPane.add(goodRecieptFrame);
			}
		});
		mnNew.add(mnItemNewGoodsReciept);
		
		JMenuItem mnItemNewLorryHire = new JMenuItem("Shipping");
		mnItemNewLorryHire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shipping lorryHireShipFrame = new Shipping();
				lorryHireShipFrame.setVisible(true);
				contentPane.add(lorryHireShipFrame);
			}
		});
		mnNew.add(mnItemNewLorryHire);
		
		//Init Add menu
		JMenu mnAdd = new JMenu("Add");
		mainMenuBar.add(mnAdd);
		
		JMenuItem mnItemAddArea = new JMenuItem("Add Area");
		mnItemAddArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddArea addStateFrame = new AddArea();
				addStateFrame.setVisible(true);
				contentPane.add(addStateFrame);
			}
		});
		mnAdd.add(mnItemAddArea);
		
		JMenuItem mnItemAddDriver = new JMenuItem("Add Driver");
		mnItemAddDriver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddDriver addCityFrame = new AddDriver();
				addCityFrame.setVisible(true);
				contentPane.add(addCityFrame);
			}
		});
		mnAdd.add(mnItemAddDriver);
		
		//Init Client Menu
		JMenu mnClient = new JMenu("Client");
		mainMenuBar.add(mnClient);
		
		JMenuItem mnItemClientAddClient = new JMenuItem("Add Client");
		mnItemClientAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAddClient addClientFrame = new ClientAddClient();
				addClientFrame.setVisible(true);
				contentPane.add(addClientFrame);
			}
		});
		mnClient.add(mnItemClientAddClient);
		
		JMenuItem mnItemClientSearchUpdate = new JMenuItem("Search/Update");
		mnItemClientSearchUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientSearchUpdate clientSearchUpdateFrame = new ClientSearchUpdate();
				clientSearchUpdateFrame.setVisible(true);
				contentPane.add(clientSearchUpdateFrame);
			}
		});
		mnClient.add(mnItemClientSearchUpdate);
		
		JMenuItem mnItemClientDelete = new JMenuItem("Detele");
		mnItemClientDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientDelete clientDeleteFrame = new ClientDelete();
				clientDeleteFrame.setVisible(true);
				contentPane.add(clientDeleteFrame);
			}
		});
		mnClient.add(mnItemClientDelete);
		
		//Init Misc menu
		JMenu mnMisc = new JMenu("Misc");
		mainMenuBar.add(mnMisc);
		
		//**SIGNOUT NOT WORKING YET.
		JMenuItem mnItemMiscSignout = new JMenuItem("Sign out");
		mnItemMiscSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainForm frame = new mainForm();
				frame.setVisible(false);
				dispose();//destroy Frame after click signout.
				LoginForm login = new LoginForm();
				login.setVisible(true);
			}
		});
		mnMisc.add(mnItemMiscSignout);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		validate();
	}
}
