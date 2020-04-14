package com.saty.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saty.springdemo.dao.CustomerDAO;
import com.saty.springdemo.entity.Customer;
import com.saty.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	
	//injecting the CustomerDAO
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get Customers from the Service
		List<Customer> theCustomers = customerService.getCustomers(); 
		
		System.out.println("No of customers: "+theCustomers.size());
		
		//add the customers to the model
		theModel.addAttribute("customers",theCustomers);
		
		
		return "list-customers";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get Customer from the Service
		Customer customer = customerService.getCustomer(theId);
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer",customer);
		
		//send over to our form
		
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete the Customer
		customerService.deleteCustomer(theId);
		
		
		return "redirect:/customer/list";
	}
	
}
