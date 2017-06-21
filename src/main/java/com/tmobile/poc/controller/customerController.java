package com.tmobile.poc.controller;

import java.net.HttpURLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tmobile.poc.service.CustomerService;
import com.tmobile.poc.vo.Customer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
public class customerController {

@Autowired
private	CustomerService service;
@ApiOperation(value = "This method is used for Saving/Adding the Customer Information!. ")
@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Data is saved/added Sucessfully!"),
		@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })

	@PostMapping( value="/api-service/v1/customers/customer/save",produces = "application/json" ,consumes="application/json")
	public ResponseEntity addCustomer(@RequestBody Customer customer) {
		try{
		service.save(customer);
		return new ResponseEntity(customer, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity("Error adding the Customer", HttpStatus.BAD_REQUEST);
		}
	}
@ApiOperation(value = "This method is used for Deleting the Customer Information!. ")
@ApiResponses(value = {
		@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Information is deleted Sucessfully!"),
		@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })

	@DeleteMapping( value="/v1/customers/customer/delete/{customerID}",produces = "application/json" /*consumes="application/json"*/)
	public ResponseEntity deleteCustomer(@PathVariable Integer customerID) {
		try{
		service.delete(customerID);
		return new ResponseEntity(customerID, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity("Error Deleting Customer", HttpStatus.BAD_REQUEST);
			
		}
	}

	@ApiOperation(value = "This method is used for Updating the Customer Information!. ")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Information is saved Sucessfully!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error Occurred while processing the request! ") })
	@PutMapping(value="/v1/customers/customer/update",produces = "application/json" ,consumes="application/json")
	public ResponseEntity updateCustomer(@RequestBody Customer customer) {
		try{
		service.saveUpdate(customer);
		return new ResponseEntity(customer, HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity("Error updating Customer", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "This method is used for getting the Customer Information by CustomerID!.")
	@ApiResponses(value = {
			@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Customer Data is fetched as Success!"),
			@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Customer Data ") })
	@GetMapping( value="/api-service/v1/customers/customer/{Id}",produces = "application/json")
	public ResponseEntity RetrieveCustomer(@PathVariable Integer Id) {
		if(Id!=0){
		 return new ResponseEntity (service.retrieve(Id),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Error finding the Customer with the Id", HttpStatus.BAD_REQUEST);	}
	}
	
}
