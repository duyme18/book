//package com.hdd.controller;
//
//import com.hdd.model.Customer;
//import com.hdd.service.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//
//@RestController
//public class CustomerRestController {
//
//    @Autowired
//    private CustomerService customerService;
//
//    //-------------------Retrieve All Customers--------------------------------------------------------
//
//    @RequestMapping(value = "/customers/", method = RequestMethod.GET)
//    public ResponseEntity<List<Customer>> listAllCustomers() {
//        List<Customer> customers = (List<Customer>) customerService.findAll();
//        if (customers.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }
//
//    //-------------------Retrieve Single Customer--------------------------------------------------------
//
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
//        System.out.println("Fetching Customer with id " + id);
//        Customer customer = customerService.findById(id);
//        if (customer == null) {
//            System.out.println("Customer with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(customer, HttpStatus.OK);
//    }
//
//    //-------------------Create a Customer--------------------------------------------------------
//
//    @RequestMapping(value = "/customers/", method = RequestMethod.POST)
//    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating Customer " + customer.getName());
//        customerService.save(customer);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
//
//    //------------------- Update a Customer --------------------------------------------------------
//
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
//        System.out.println("Updating Customer " + id);
//
//        Customer currentCustomer = customerService.findById(id);
//
//        if (currentCustomer == null) {
//            System.out.println("Customer with id " + id + " not found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        currentCustomer.setPhone(customer.getPhone());
//        currentCustomer.setJob(customer.getJob());
//        currentCustomer.setAddress(customer.getAddress());
//        currentCustomer.setBirthday(customer.getBirthday());
//        currentCustomer.setName(customer.getName());
//        currentCustomer.setId(customer.getId());
//
//        customerService.save(currentCustomer);
//        return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
//    }
//
//    //------------------- Delete a Customer --------------------------------------------------------
//
//    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
//        System.out.println("Fetching & Deleting Customer with id " + id);
//
//        Customer customer = customerService.findById(id);
//        if (customer == null) {
//            System.out.println("Unable to delete. Customer with id " + id + " not found");
//            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
//        }
//
//        customerService.remove(id);
//        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
//    }
//}