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
import java.util.ArrayList;

public class CustomerManagementUI {
    //frame for the customer information
    private JFrame customerFrame;
    //panel for customer specific information
    private JPanel customerPanel;
    //panel for customer address information
    private JPanel addressPanel;
    //panel for customer buttons (add, delete, etc.)
    private JPanel customerButtonPanel;
    //panel for customer address information
    private JPanel orderPanel;
    //panel for customer buttons (add, delete, etc.)
    private JPanel orderButtonPanel;

    //textFields for customer information
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;

    //text fields for order information
    private JTextField numberField;
    private JTextField dateField;
    private JComboBox customerComboBox;
    private JComboBox itemComboBox;
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

    /*
     * Customer Menu Panels
     */
    public void createCustomerPanel()
    {
        //Customer information
        customerPanel = new JPanel();
        customerPanel.setSize(1000,200);
        customerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //create content
        JLabel nameLabel = new JLabel("Name");
        nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone");
        phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email");
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
    }

    public void createAddressPanel()
    {
        //Address information
        addressPanel = new JPanel();
        customerPanel.setSize(1000,200);
        addressPanel.setLayout(new GridBagLayout());
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address"));

        //create content
        JLabel streetLabel = new JLabel("Street");
        streetField = new JTextField();
        streetField.setColumns(10);
        JLabel cityLabel = new JLabel("City");
        cityField = new JTextField();
        cityField.setColumns(10);
        JLabel stateLabel = new JLabel("State");
        stateField = new JTextField();
        stateField.setColumns(10);
        JLabel zipCodeLabel = new JLabel("Zip Code");
        zipCodeField = new JTextField();
        zipCodeField.setColumns(10);

        //add to panel
        GridBagConstraints constraints = new GridBagConstraints();
	    constraints.ipady = 10;
        constraints.ipadx = 300;
	    constraints.weightx = 1.0;
	    constraints.gridwidth = 1;
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
    }

    public void createCustomerButtonPanel()
    {
        //Customer buttons
        customerButtonPanel = new JPanel();
        customerButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //create content
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCustomer();
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        //add to buttonPanel
        customerButtonPanel.add(searchButton);
        customerButtonPanel.add(addButton);
        customerButtonPanel.add(updateButton);
        customerButtonPanel.add(deleteButton);
    }

    /*
     * Order Menu Panels
     */
    public void createOrderPanel()
    {
        //Customer information
        orderPanel = new JPanel();
        orderPanel.setSize(1000,200);
        orderPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //create content
        JLabel numberLabel = new JLabel("Number");
        numberField = new JTextField();
        JLabel dateLabel = new JLabel("Date");
        dateField = new JTextField();
        JLabel customerLabel = new JLabel("Customer");
        Customer[] customers = getCustomers();
        customerComboBox = new JComboBox<>(customers);
        JLabel itemLabel = new JLabel("Item");
        String[] items = {"Caesar Salad", "Greek Salad", "Cobb Salad"};
        itemComboBox = new JComboBox<>(items);
        JLabel priceLabel = new JLabel("Price ($)");
        priceField = new JTextField();

        //add to panel
        constraints.fill = GridBagConstraints.HORIZONTAL;
	    constraints.ipady = 10;
	    constraints.weightx = 1.0;
	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
        orderPanel.add(numberLabel, constraints);

	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 1;
        orderPanel.add(numberField, constraints);

	    constraints.gridwidth = 1;
	    constraints.gridx = 4;
	    constraints.gridy = 0;
        orderPanel.add(dateLabel, constraints);

	    constraints.gridwidth = 1;
	    constraints.gridx = 4;
	    constraints.gridy = 1;
        orderPanel.add(dateField, constraints);

	    constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 2;
        orderPanel.add(customerLabel, constraints);

	    constraints.gridwidth = 8;
	    constraints.gridx = 0;
	    constraints.gridy = 3;
        orderPanel.add(customerComboBox, constraints);

        constraints.gridwidth = 1;
	    constraints.gridx = 0;
	    constraints.gridy = 4;
        orderPanel.add(itemLabel, constraints);

        constraints.gridwidth = 4;
	    constraints.gridx = 0;
	    constraints.gridy = 5;
        orderPanel.add(itemComboBox, constraints);

        constraints.gridwidth = 1;
	    constraints.gridx = 4;
	    constraints.gridy = 4;
        orderPanel.add(priceLabel, constraints);

        constraints.gridwidth = 1;
	    constraints.gridx = 4;
	    constraints.gridy = 5;
        orderPanel.add(priceField, constraints);
    }

    public void createOrderButtonPanel()
    {
        //Customer buttons
        orderButtonPanel = new JPanel();
        orderButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //create content
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchOrder();
            }
        });
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });

        //add to buttonPanel
        orderButtonPanel.add(searchButton);
        orderButtonPanel.add(addButton);
        orderButtonPanel.add(updateButton);
        orderButtonPanel.add(deleteButton);
    }

    /*
     * Create and display JFrames
     */
    public void createAndShowGUI() 
    {
        //Customer information
        customerFrame = new JFrame("Customer");
        customerFrame.setSize(1000,500);
        customerFrame.setLocation(20, 250);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setLayout(new BoxLayout(customerFrame.getContentPane(), BoxLayout.Y_AXIS));

        //create panels
        createCustomerPanel();
        createAddressPanel();
        createCustomerButtonPanel();

        //add to customerFrame
        customerFrame.add(customerPanel);
        customerFrame.add(addressPanel);
        customerFrame.add(customerButtonPanel);

        //Order information
        orderFrame = new JFrame("Order");
        orderFrame.setSize(1000,500);
        orderFrame.setLocation(900, 300);
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderFrame.setLayout(new BoxLayout(orderFrame.getContentPane(), BoxLayout.Y_AXIS));
        
        //create panels
        createOrderPanel();
        createOrderButtonPanel();

        //add to orderFrame
        orderFrame.add(orderPanel);
        orderFrame.add(orderButtonPanel);

        //set visible
        orderFrame.setVisible(true);
        customerFrame.setVisible(true);
    }

    private Customer[] getCustomers()
    {
        return customerService.getCustomers();
    }

    /*
     * Database functions
     */
    private void searchCustomer()
    {
        String name = nameField.getText();
        try {
            Customer customer = customerService.getCustomer(name);
            Address address = addressService.getAddress(customer.getAddressId());
            
            //set fields to customer/address info if found
            phoneField.setText(customer.getPhone());
            emailField.setText(customer.getEmail());
            streetField.setText(address.getStreet());
            cityField.setText(address.getCity());
            stateField.setText(address.getState());
            zipCodeField.setText(address.getZipCode()+"");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Customer not found");
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCustomer() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        int zipCode = Integer.parseInt(zipCodeField.getText());

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

        addressService.addAddress(address);
        System.out.println("Customer address : " + address);
        customerService.addCustomer(customer);
        System.out.println("Customer : " + customer);

        //Show popup
        JOptionPane.showMessageDialog(customerFrame, "Customer added successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);

        // Clear fields after saving
        clearFields();
        //update customers combo box
        customerComboBox.setModel(new DefaultComboBoxModel(getCustomers()));
    }

    private void updateCustomer()
    {
        String name = nameField.getText();
        try {            
            //get fields
            String phone = phoneField.getText();
            String email = emailField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            int zipCode = Integer.parseInt(zipCodeField.getText());

            //update customer information
            Customer customer = customerService.updateCustomer(name, phone, email);
            addressService.updateAddress(customer.getAddressId(), street, city, state, zipCode);
            
            //Show popup
            JOptionPane.showMessageDialog(customerFrame, "Customer updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            //update customers combo box
            customerComboBox.setModel(new DefaultComboBoxModel(getCustomers()));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Customer not found");
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCustomer()
    {
        //get name
        String name = nameField.getText();
        try {
            //delete customer
            Customer customer = customerService.deleteCustomer(name);
            //use reference to deleted customer to delete address
            addressService.deleteAddress(customer.getAddressId());

            //popup success
            JOptionPane.showMessageDialog(customerFrame, "Customer deleted successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            //update customers combo box
            customerComboBox.setModel(new DefaultComboBoxModel(getCustomers()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }

    private void searchOrder()
    {
        String name = nameField.getText();
        try {
            Customer customer = customerService.getCustomer(name);
            Address address = addressService.getAddress(customer.getAddressId());
            
            //set fields to customer/address info if found
            phoneField.setText(customer.getPhone());
            emailField.setText(customer.getEmail());
            streetField.setText(address.getStreet());
            cityField.setText(address.getCity());
            stateField.setText(address.getState());
            zipCodeField.setText(address.getZipCode()+"");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Customer not found");
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addOrder() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        int zipCode = Integer.parseInt(zipCodeField.getText());

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

        addressService.addAddress(address);
        System.out.println("Customer address : " + address);
        customerService.addCustomer(customer);
        System.out.println("Customer : " + customer);

        //Show popup
        JOptionPane.showMessageDialog(customerFrame, "Customer added successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);

        // Clear fields after saving
        clearFields();
    }

    private void updateOrder()
    {
        String name = nameField.getText();
        try {            
            //get fields
            String phone = phoneField.getText();
            String email = emailField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            int zipCode = Integer.parseInt(zipCodeField.getText());

            //update customer information
            Customer customer = customerService.updateCustomer(name, phone, email);
            addressService.updateAddress(customer.getAddressId(), street, city, state, zipCode);
            
            //Show popup
            JOptionPane.showMessageDialog(customerFrame, "Customer updated successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Customer not found");
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteOrder()
    {
        //get name
        String name = nameField.getText();
        try {
            //delete customer
            Customer customer = customerService.deleteCustomer(name);
            //use reference to deleted customer to delete address
            addressService.deleteAddress(customer.getAddressId());

            //popup success
            JOptionPane.showMessageDialog(customerFrame, "Customer deleted successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(customerFrame, "Customer not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }

    private void clearFields()
    {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipCodeField.setText("");
        dateField.setText("");
    }

    /*
     * Main, obviously
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CustomerManagementUI ui = new CustomerManagementUI();
                ui.createAndShowGUI();
            }
        });
    }
}

