package main.java.com.example.ui;

import com.example.model.Address;
import com.example.model.Customer;
import com.example.model.CustomerOrder;
import com.example.service.AddressService;
import com.example.service.CustomerOrderService;
import com.example.service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerManagementUI {
    //frame for the customer information
    private JFrame customerFrame;
    //panel for customer specific information
    private JPanel customerPanel;
    //panel for customer address information
    private JPanel addressPanel;
    //panel for customer buttons (add, delete, etc.)
    private JPanel customerButtonPanel;

    //textFields for customer information
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;

    //text fields for order information
    private JTextField orderDateField;
    private JTextField itemField;
    private JTextField priceField;

    //frame for order information
    private JFrame orderFrame;
    //references to service classes
    private CustomerService customerService;
    private AddressService addressService;
    private CustomerOrderService customerOrderService;


    public CustomerManagementUI() 
    {
        this.customerService = new CustomerService();
        this.addressService = new AddressService();
        this.customerOrderService = new CustomerOrderService();
    }

    public void createAndShowGUI() 
    {
        //Customer information
        customerFrame = new JFrame("Customer");
        customerFrame.setSize(1000,500);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //customerFrame.setLayout();
        customerFrame.setLayout(new GridLayout(3,1));

        //Customer information
        customerPanel = new JPanel();
        customerPanel.setSize(1000,200);
        customerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //create content
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        //add to panel
        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.ipady = 10;
	    constraints.weightx = 1.0;
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
        customerPanel.add(nameLabel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridwidth = 2;
	    constraints.gridx = 0;
	    constraints.gridy = 1;
        customerPanel.add(nameField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 2;
        customerPanel.add(phoneLabel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 3;
        customerPanel.add(phoneField, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridwidth = 1;
	    constraints.gridx = 1;
	    constraints.gridy = 2;
        customerPanel.add(emailLabel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.gridwidth = 1;
	    constraints.gridx = 1;
	    constraints.gridy = 3;
        customerPanel.add(emailField, constraints);

        //Address information
        addressPanel = new JPanel();
        addressPanel.setLayout(new GridBagLayout());
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));

        //create content
        JLabel streetLabel = new JLabel("Street:");
        streetField = new JTextField();
        streetField.setColumns(10);
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();
        cityField.setColumns(10);
        JLabel stateLabel = new JLabel("State:");
        stateField = new JTextField();
        stateField.setColumns(10);
        JLabel zipCodeLabel = new JLabel("Zip Code:");
        zipCodeField = new JTextField();
        zipCodeField.setColumns(10);

        JLabel orderDateLabel = new JLabel("Order Date:");
        orderDateField = new JTextField();
        JLabel itemLabel = new JLabel("Item:");
        itemField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();

        //add to panel
	    constraints.gridx = 0;
	    constraints.gridy = 0;
        addressPanel.add(streetLabel, constraints);
        constraints.gridx = 0;
	    constraints.gridy = 1;
        addressPanel.add(streetField, constraints);

        constraints.gridx = 1;
	    constraints.gridy = 0;
        addressPanel.add(cityLabel, constraints);
        constraints.gridx = 1;
	    constraints.gridy = 1;
        addressPanel.add(cityField, constraints);

        constraints.gridx = 0;
	    constraints.gridy = 2;
        addressPanel.add(stateLabel, constraints);
        constraints.gridx = 0;
	    constraints.gridy = 3;
        addressPanel.add(stateField, constraints);

        constraints.gridx = 1;
	    constraints.gridy = 2;
        addressPanel.add(zipCodeLabel, constraints);
        constraints.gridx = 1;
	    constraints.gridy = 3;
        addressPanel.add(zipCodeField, constraints);

        //Customer buttons
        customerButtonPanel = new JPanel();
        customerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //create content
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCustomer();
            }
        });

        //add to buttonPanel
        customerButtonPanel.add(saveButton);

        //add to customerFrame
        customerFrame.add(customerPanel);
        customerFrame.add(addressPanel);
        customerFrame.add(customerButtonPanel);

        //set visible
        customerFrame.setVisible(true);
    }

    private void saveCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        int zipCode = Integer.parseInt(zipCodeField.getText());
        String orderDate = orderDateField.getText();
        String item = itemField.getText();
        float price = Float.parseFloat(priceField.getText());

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setDate(orderDate);
        customerOrder.setItem(item);
        customerOrder.setPrice(price);
        customerOrder.setCustomer(customer);

        addressService.saveAddress(address);
        customerService.saveCustomer(customer);
        customerOrderService.saveCustomerOrder(customerOrder);

        // Clear fields after saving
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipCodeField.setText("");
        orderDateField.setText("");
        itemField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CustomerManagementUI ui = new CustomerManagementUI();
                ui.createAndShowGUI();
            }
        });
    }
}

