package de.axone.repository;

import de.axone.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.lastName LIKE CONCAT('%',:query, '%') Or c.firstName LIKE CONCAT('%', :query, '%')")
    List<Customer> findAllByQuery(String query);
}
