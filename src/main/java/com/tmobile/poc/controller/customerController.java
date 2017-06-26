package com.tmobile.poc.controller;
import java.net.HttpURLConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tmobile.poc.IConstants;
import com.tmobile.poc.repository.CustomerDAORepository;
import com.tmobile.poc.vo.Customer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CustomerController {
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	@Autowired
	private CustomerDAORepository service;
	@ApiOperation(value = "This method is used for Saving/Adding the Customer Information!. ")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Data is saved/added Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })

	@PostMapping(value = "/api-service/v1/customer/save", produces = "application/json")
	public ResponseEntity saveCustomer(@RequestBody(required = true) Customer customer) {
		try {
			service.save(customer);
			return new ResponseEntity(customer, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Error adding the Customer", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "This method is used for Deleting the Customer Information!. ")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Information is deleted Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })
	//@RequestMapping(value = "/v1/customer/delete/{customerId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/v1/customer/delete/{customerId}", produces = "application/json")
	public ResponseEntity e(@PathVariable(required = true) Integer customerId) {
		try {

			service.deleteCustomer(customerId,IConstants.INACTIVE_STR);
			logger.info("I m here");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Error Deleting Customer", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity("Successfully Deleted Customer",HttpStatus.OK);
	}

	@ApiOperation(value = "This method is used for Updating the Customer Information!. ")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Information is saved Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })
	@PutMapping(value = "/v1/customer/update", produces = "application/json")
	public ResponseEntity updateCustomerInfo(@RequestBody(required = true) Customer customer) {
		try {

			service.save(customer);
			return new ResponseEntity(customer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity("Error updating Customer", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "This method is used for getting the Customer Information by CustomerID!.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Data is fetched as Success!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Customer Data ") })
	@GetMapping(value = "/api-service/v1/customer/{customerId}", produces = "application/json")
	public ResponseEntity getCustomerInfo(@PathVariable(required = true) Integer customerId) {
		if (customerId > 0) {
			return new ResponseEntity(service.findOne(customerId), HttpStatus.OK);
		} else {
			return new ResponseEntity("Error finding the Customer with this Id", HttpStatus.BAD_REQUEST);
		}
	}

}
