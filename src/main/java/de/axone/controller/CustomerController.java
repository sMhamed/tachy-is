package de.axone.controller;

import de.axone.model.Customer;
import de.axone.model.Vehicle;
import de.axone.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tachy/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam("query") String query) {
        return new ResponseEntity<>(customerService.searchCustomers(query), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/vehicle")
    public ResponseEntity<Vehicle> getCustomerVehicle(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(customerService.getCustomerVehicle(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }
}
