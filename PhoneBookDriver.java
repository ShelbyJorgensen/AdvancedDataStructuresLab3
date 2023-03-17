/**
 * @author Shelby Jorgensen
 * Phone Book driver, establishes the phone book GUI
 * Established a phone book binary tree, entries can be added, modified,
 * deleted, searched, saved and loaded
 */

package Lab3;

import java.awt.EventQueue;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollBar;

public class PhoneBookDriver extends JFrame {
	// Create the phone book tree to store people entries
	BinarySearchTree<Person> phoneBook = new BinarySearchTree<Person>();
	
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneNumberField;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel phoneNumberLabel;
	private JTextPane displayField;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JLabel lblNewLabel;
	private String fileName;
	private JButton btnNewButton_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneBookDriver frame = new PhoneBookDriver();
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
	public PhoneBookDriver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// Establish the grid layout to organize GUI components
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{106, 321, 0, 162, 0, 0};
		gbl_contentPane.rowHeights = new int[]{57, 50, 50, 50, 53, 50, 50, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// App header
		lblNewLabel = new JLabel("Phone Book");
		lblNewLabel.setForeground(new Color(153, 0, 51));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		// Name label next to name input field
		nameLabel = new JLabel("Name:");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		contentPane.add(nameLabel, gbc_nameLabel);
		
		// Name input field
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.BOTH;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		contentPane.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		/**
		 * Add contant button handler, takes input from all three fields and creates a person,
		 * then adds them to the phone book
		 */
		btnNewButton = new JButton("Add Contact");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String givenName = nameField.getText();
				String givenAddress = addressField.getText();
				String givenPhoneNumber = phoneNumberField.getText();
				Person newPerson = new Person(givenName, givenAddress, givenPhoneNumber);
				phoneBook.add(newPerson);
				
				clearInputFields();
			}
		});
		
		// Add contact button layout
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		/**
		 * Remove contact button handler, finds a person based off the name provided,
		 * removing them from the phone book
		 */
		btnNewButton_3 = new JButton("Remove Contact");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String givenName = nameField.getText();
				Iterator<Person> itr = phoneBook.iterator(); 
				
				while(itr.hasNext()) {
					Person temp = itr.next();
					if(temp.getName().equals(givenName)) {
						phoneBook.remove(temp);
						displayField.setText(givenName + " was removed from your phone book.");
					}
				}
				clearInputFields();
			}
		});
		
		// Remove contact button layout
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 1;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		// Label for phone number input field
		phoneNumberLabel = new JLabel("Phone Number:");
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_phoneNumberLabel.gridx = 0;
		gbc_phoneNumberLabel.gridy = 2;
		contentPane.add(phoneNumberLabel, gbc_phoneNumberLabel);
		
		// Phone number input field
		phoneNumberField = new JTextField();
		GridBagConstraints gbc_phoneNumberField = new GridBagConstraints();
		gbc_phoneNumberField.fill = GridBagConstraints.BOTH;
		gbc_phoneNumberField.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberField.gridx = 1;
		gbc_phoneNumberField.gridy = 2;
		contentPane.add(phoneNumberField, gbc_phoneNumberField);
		phoneNumberField.setColumns(10);
		
		/**
		 * Modify contant button handler, takes a name from input field,
		 * changes the phone number and address to those provided in the respective fields
		 */
		btnNewButton_1 = new JButton("Modify Contact");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String givenName = nameField.getText();
				if(givenName.equals("")) {
					displayField.setText("Please enter the name of the contact you want to modify.");
				} else {
					Iterator<Person> itr = phoneBook.iterator();
					while(itr.hasNext()) {
						Person temp = itr.next();
						if(temp.getName().equals(givenName)) {
							// Remove old contact entry
							phoneBook.remove(temp);
							
							// Get new contact info
							String givenAddress = addressField.getText();
							String givenPhoneNumber = phoneNumberField.getText();
							
							// Update contact info, then readd entry into the phone book
							temp.setAddress(givenAddress);
							temp.setPhoneNumber(givenPhoneNumber);
							phoneBook.add(temp);
						}
					}
				}
				clearInputFields();
			}
		});
		
		// Modify contact button layout
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		/**
		 * Find contact button handler
		 * Takes name from name field and finds the person associated with that name from phone book
		 */
		btnNewButton_4 = new JButton("Find Contact");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String givenName = nameField.getText();
				if(givenName.equals("")) {
					displayField.setText("Please enter the name of the contact you want to search for.");
				} else {
					Iterator<Person> itr = phoneBook.iterator();
					while(itr.hasNext()) {
						Person temp = itr.next();
						if(temp.getName().equals(givenName)) {
							displayField.setText(temp.getName() + "\n" + temp.getPhoneNumber() + "\n" + temp.getAddress() + "\n");
						}
					}
				}
				clearInputFields();
			}
		});
		
		// Find contact button layout
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 2;
		contentPane.add(btnNewButton_4, gbc_btnNewButton_4);
		
		// Address field label
		addressLabel = new JLabel("Address:");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_addressLabel.anchor = GridBagConstraints.EAST;
		gbc_addressLabel.gridx = 0;
		gbc_addressLabel.gridy = 3;
		contentPane.add(addressLabel, gbc_addressLabel);
		
		// Address input field
		addressField = new JTextField();
		GridBagConstraints gbc_addressField = new GridBagConstraints();
		gbc_addressField.fill = GridBagConstraints.BOTH;
		gbc_addressField.insets = new Insets(0, 0, 5, 5);
		gbc_addressField.gridx = 1;
		gbc_addressField.gridy = 3;
		contentPane.add(addressField, gbc_addressField);
		addressField.setColumns(10);
		
		/**
		 * Print phone book action handler
		 * Prints out all contents of phone book on click
		 */
		btnNewButton_2 = new JButton("Print Phone Book");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayField.setText(printPhoneBook());
			}
		});
		
		// Print phone book button layout
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 3;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		/**
		 * Clear phone book action handler
		 * Clears out all contents of the phone book
		 */
		btnNewButton_8 = new JButton("Clear Phone Book");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Iterator<Person> itr = phoneBook.iterator();
				while(itr.hasNext()) {
					Person temp = itr.next();
					phoneBook.remove(temp);
				}
				displayField.setText("Your phone book is now empty.");
			}
		});
		
		// Clear phone book button layout
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8.gridx = 3;
		gbc_btnNewButton_8.gridy = 3;
		contentPane.add(btnNewButton_8, gbc_btnNewButton_8);
		
		// Display field, will display any text providing user with feed back
		displayField = new JTextPane();
		GridBagConstraints gbc_displayField = new GridBagConstraints();
		gbc_displayField.gridwidth = 3;
		gbc_displayField.insets = new Insets(0, 0, 5, 5);
		gbc_displayField.fill = GridBagConstraints.BOTH;
		gbc_displayField.gridx = 1;
		gbc_displayField.gridy = 5;
		contentPane.add(displayField, gbc_displayField);
		
		/**
		 * Clear display action handler
		 * Used to clear display field
		 */
		btnNewButton_5 = new JButton("Clear Display");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayField.setText("");
			}
		});
		
		// Clear display button layout
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 6;
		contentPane.add(btnNewButton_5, gbc_btnNewButton_5);
		
		
		/**
		 * Save phone book action handler
		 * Used to save contents of phone book into a .ser file
		 * Serializes phone book object and person objects inside phone book
		 */
		btnNewButton_6 = new JButton("Save Phone Book to File");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Save phone book into hard coded file name, saves current contents of phone book object
					FileOutputStream fos = new FileOutputStream("PhoneBook.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(phoneBook);
					oos.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Save phone book layout
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_6.gridx = 2;
		gbc_btnNewButton_6.gridy = 6;
		contentPane.add(btnNewButton_6, gbc_btnNewButton_6);
		
		/**
		 * Load old phone book action handler
		 * Used to load old phone book from a .ser file
		 */
		btnNewButton_7 = new JButton("Load Old Phone Book");
		btnNewButton_7.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				try {
					// Take in hard coded input file, and load its contents into phone book var
					FileInputStream fis = new FileInputStream("PhoneBook.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					phoneBook = (BinarySearchTree<Person>)ois.readObject();
					ois.close();
					displayField.setText("Your old phone book: \n" + printPhoneBook());
				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		// Load old phone book layout
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_7.gridx = 3;
		gbc_btnNewButton_7.gridy = 6;
		contentPane.add(btnNewButton_7, gbc_btnNewButton_7);
	}
	
	/**
	 * Used to reset all input fields to empty
	 */
	public void clearInputFields() {
		nameField.setText("");
		addressField.setText("");
		phoneNumberField.setText("");
	}
	
	/**
	 * Provides structure to how the phone book contents are printed
	 * @String containing all phone book contents
	 */
	public String printPhoneBook() {
		Iterator<Person> itr = phoneBook.iterator();
		String result = "";
		
		while(itr.hasNext()) {
			result += itr.next().toString() + "";
		}
		
		return result;
	}
}
