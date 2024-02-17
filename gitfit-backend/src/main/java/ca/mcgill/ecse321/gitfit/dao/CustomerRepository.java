package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findCustomerById(int id);
}
