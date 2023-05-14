package com.example.dao;

import com.example.model.Customer;
import com.example.util.HibernateUtil;
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
            String hql = "FROM Customer WHERE name = :customerName";
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName", name);
            return query.uniqueResult();
        }
    }
}
