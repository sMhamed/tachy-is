package de.axone.service;

import de.axone.model.Customer;
import de.axone.model.Vehicle;
import de.axone.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> searchCustomers(String query) {
        return customerRepository.findAllByQuery(query);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    public Vehicle getCustomerVehicle(Long id) {
        Customer customer = this.getCustomerById(id);
        return customer.getVehicle();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }
}
