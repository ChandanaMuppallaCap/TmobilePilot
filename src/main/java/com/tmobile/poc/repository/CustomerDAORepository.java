package com.tmobile.poc.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tmobile.poc.vo.Customer;

@Repository
public interface CustomerDAORepository extends CrudRepository<Customer, Integer>{

	

}
