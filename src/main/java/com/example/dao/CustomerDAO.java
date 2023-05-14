package com.example.dao;

import com.example.model.Customer;
import com.example.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CustomerDAO {
    public void saveCustomer(Customer customer) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(customer);

            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer getCustomer(String name)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //get customer with hql
            String hql = "FROM Customer WHERE name = :customerName";
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName", name);

            //since it's using a non-primary key
            return query.uniqueResult();
        }
    }

    public void updateCustomer(String name, String phone, String email)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //update customer with hql
            String hql = "UPDATE Customer SET phone = :newPhone, email = :newEmail WHERE name = :customerName";
            Query query = session.createQuery(hql);
            query.setParameter("newPhone", phone);
            query.setParameter("newEmail", email);
            query.setParameter("customerName", name);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }

    public void deleteCustomer(String name)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //get customer with hql
            String hql = "DELETE FROM Customer WHERE name = :customerName";
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName", name);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }

    public List<Customer> getCustomers()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            //get customer with hql
            String hql = "FROM Customer";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            return query.list();
        }
    }
}
