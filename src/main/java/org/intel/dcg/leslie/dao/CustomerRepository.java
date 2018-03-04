package org.intel.dcg.leslie.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.intel.dcg.leslie.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
