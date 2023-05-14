package com.example.service;

import com.example.dao.AddressDAO;
import com.example.model.Address;

public class AddressService {
    private AddressDAO addressDAO;

    public AddressService() {
        this.addressDAO = new AddressDAO();
    }

    public void addAddress(Address address) {
        addressDAO.saveAddress(address);
    }

    public Address getAddress(int id)
    {
        return addressDAO.getAddress(id);
    }
}
