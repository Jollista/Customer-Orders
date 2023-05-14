package com.example.service;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }
}
