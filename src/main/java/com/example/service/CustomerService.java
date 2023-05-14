package com.example.service;

import java.util.List;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void addCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    public Customer getCustomer(String name)
    {
        return customerDAO.getCustomer(name);
    }

    public Customer updateCustomer(String name, String phone, String email)
    {
        customerDAO.updateCustomer(name, phone, email);
        return getCustomer(name);
    }

    public Customer deleteCustomer(String name)
    {
        Customer cust = customerDAO.getCustomer(name);
        customerDAO.deleteCustomer(name);
        return cust;
    }

    public Customer[] getCustomers()
    {
        List<Customer> customers = customerDAO.getCustomers();
        Customer[] cust = new Customer[1];
        return customers.toArray(cust);
    }
}
