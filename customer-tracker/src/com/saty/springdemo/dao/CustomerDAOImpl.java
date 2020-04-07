package com.saty.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.saty.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomersList() {
		
		//get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create a Query
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName",Customer.class);
				
		//Execute Query and get Results
		List<Customer> customers = theQuery.getResultList();
		
		System.out.println("No of customers :"+customers.size());
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get Current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		//get the Current Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve the customer based on his Id
		Customer theCustomer = currentSession.get(Customer.class,theId);
		
		//return the Customer
		return theCustomer;
		
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get the current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		
		theQuery.executeUpdate();
		
	}

}
