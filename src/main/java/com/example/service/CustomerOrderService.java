package com.example.service;

import com.example.dao.CustomerOrderDAO;
import com.example.model.CustomerOrder;

public class CustomerOrderService {
    private CustomerOrderDAO customerOrderDAO;

    public CustomerOrderService() {
        this.customerOrderDAO = new CustomerOrderDAO();
    }

    public void saveCustomerOrder(CustomerOrder customerOrder) {
        customerOrderDAO.saveCustomerOrder(customerOrder);
    }
}
