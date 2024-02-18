package ca.mcgill.ecse321.gitfit.repository;

import ca.mcgill.ecse321.gitfit.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findCustomerByCustomerId(int id);
}
