package com.saty.springdemo.dao;

import java.util.List;
import com.saty.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomersList();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
