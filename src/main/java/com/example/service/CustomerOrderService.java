package com.example.service;

import com.example.dao.CustomerOrderDAO;
import com.example.model.Customer;
import com.example.model.CustomerOrder;

public class CustomerOrderService {
    private CustomerOrderDAO customerOrderDAO;

    public CustomerOrderService() {
        this.customerOrderDAO = new CustomerOrderDAO();
    }

    public void addCustomerOrder(CustomerOrder customerOrder) {
        customerOrderDAO.saveCustomerOrder(customerOrder);
    }

    public CustomerOrder getOrder(int id)
    {
        return customerOrderDAO.getOrder(id);
    }

    public void updateOrder(int number, String date, Customer customer, String item, float price)
    {
        customerOrderDAO.updateOrder(number, date, customer.getId(), item, price);
    }

    public void deleteOrder(int number)
    {
        customerOrderDAO.deleteOrder(number);
    }
}
