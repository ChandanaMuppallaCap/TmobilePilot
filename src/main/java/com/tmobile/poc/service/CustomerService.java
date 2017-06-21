package com.tmobile.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.tmobile.poc.repository.CustomerDAORepository;
import com.tmobile.poc.vo.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAORepository repository;
	
	public void save(Customer customer)
	{
		repository.save(customer);
	}
	public void delete(Integer id)
	{
		
		repository.delete(id);
	}
	
	public void saveUpdate(Customer customer)
	{
		repository.save(customer);
		
	}
	
	public Customer retrieve(Integer Id)
	{
		return repository.findOne(Id);
	}
}
