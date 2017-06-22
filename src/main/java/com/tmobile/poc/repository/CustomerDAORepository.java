package com.tmobile.poc.repository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tmobile.poc.vo.Customer;

@Repository
public interface CustomerDAORepository extends CrudRepository<Customer, Integer> ,IConstants {

 
	@Modifying
	@Transactional
	@Query("Update  Customer c set  c.status = :status where c.customerId= :customerId")
	
	void deletecustomer(@Param("customerId") Integer customerId,@Param("status") Integer status);

}
