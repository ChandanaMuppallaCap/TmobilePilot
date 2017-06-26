package com.tmobile.poc.repository;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.tmobile.poc.IConstants;
import com.tmobile.poc.vo.CustomerVO;

@Repository

public interface CustomerDAORepository extends CrudRepository<CustomerVO, Integer> ,IConstants {

 
	@Modifying
	@Transactional
	@Query("Update  CustomerVO c set  c.status = :status where c.customerId= :customerId")
	
	void deleteCustomer(@Param("customerId") Integer customerId,@Param("status") int status);

}
