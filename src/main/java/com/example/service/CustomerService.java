package com.example.service;

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
}
