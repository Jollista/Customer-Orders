package com.example.service;

import com.example.dao.AddressDAO;
import com.example.model.Address;

public class AddressService {
    private AddressDAO addressDAO;

    public AddressService() {
        this.addressDAO = new AddressDAO();
    }

    public void saveAddress(Address address) {
        addressDAO.saveAddress(address);
    }
}
