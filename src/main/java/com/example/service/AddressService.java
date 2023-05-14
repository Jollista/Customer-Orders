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

    public Address updateAddress(int id, String street, String city, String state, int zipCode)
    {
        addressDAO.updateAddress(id, street, city, state, zipCode);
        return getAddress(id);
    }

    public Address deleteAddress(int id)
    {
        Address addr = addressDAO.getAddress(id);
        addressDAO.deleteAddress(id);
        return addr;
    }
}
