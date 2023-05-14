package com.example.service;

import com.example.dao.CustomerOrderDAO;
import com.example.model.Customer;
import com.example.model.Customer_order;

public class CustomerOrderService {
    private CustomerOrderDAO customerOrderDAO;

    public CustomerOrderService() {
        this.customerOrderDAO = new CustomerOrderDAO();
    }

    public void addCustomerOrder(Customer_order customerOrder) {
        customerOrderDAO.saveCustomerOrder(customerOrder);
    }

    public Customer_order getOrder(int id)
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
