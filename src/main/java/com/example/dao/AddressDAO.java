package com.example.dao;

import com.example.model.Address;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AddressDAO {
    public void saveAddress(Address address) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(address);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Address getAddress(int id)
    {
        Transaction transaction = null;
        Address address = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            address = session.get(Address.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return address;
    }

    public void updateAddress(int id, String street, String city, String state, int zipCode)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //update address with hql
            String hql = "UPDATE Address SET street = :newStreet, city = :newCity, state = :newState, zip_code = :newZip WHERE id = :addressID";
            Query query = session.createQuery(hql);
            query.setParameter("newStreet", street);
            query.setParameter("newCity", city);
            query.setParameter("newState", state);
            query.setParameter("newZip", zipCode);
            query.setParameter("addressID", id);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }

    public void deleteAddress(int id)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //get customer with hql
            String hql = "DELETE FROM Address WHERE id = :addressID";
            Query<Address> query = session.createQuery(hql);
            query.setParameter("addressID", id);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }
}
