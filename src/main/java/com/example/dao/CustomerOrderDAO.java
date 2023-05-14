package com.example.dao;

import com.example.model.Customer_order;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CustomerOrderDAO {
    public void saveCustomerOrder (Customer_order customerOrder) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(customerOrder);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Customer_order getOrder (int number)
    {
        Transaction transaction = null;
        Customer_order order = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            order = session.get(Customer_order.class, number);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return order;
    }

    public void updateOrder (int number, String date, int customerID, String item, float price)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //update customer with hql
            String hql = "UPDATE Customer_order SET date = :newDate, item = :newItem, price = :newPrice, customer_id = :newCustomerID WHERE number = :orderNumber";
            Query query = session.createQuery(hql);
            query.setParameter("newDate", date);
            query.setParameter("newItem", item);
            query.setParameter("newPrice", price);
            query.setParameter("newCustomerID", customerID);
            query.setParameter("orderNumber", number);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }

    public void deleteOrder (int number)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            //get customer with hql
            String hql = "DELETE FROM Customer_order WHERE number = :orderNumber";
            Query<Customer_order> query = session.createQuery(hql);
            query.setParameter("orderNumber", number);

            //execute update
            query.executeUpdate();

            //commit if no errors
            session.getTransaction().commit();
        }
    }
}
