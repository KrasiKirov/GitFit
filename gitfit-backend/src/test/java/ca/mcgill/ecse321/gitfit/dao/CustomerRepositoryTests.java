package ca.mcgill.ecse321.gitfit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.model.Customer;

@SpringBootTest
public class CustomerRepositoryTests {
    @Autowired
    private CustomerRepository customerRepository;

    @AfterEach
    public void clearDatabase() {
        customerRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCustomer() {
        // Create a customer
        String email = "kliment.kirk@gmail.com";
        String password = "password123";
        String lastName = "Kirk";
        String firstName = "Kliment";
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);

        // Save customer
        customerRepository.save(customer);

        // Read customer from database
        customer = customerRepository.findCustomerByUsername(customer.getUsername());

        // Assert that the customer is not null and has correct attributes
        assertNotNull(customer);
        assertEquals(email, customer.getEmail());
        assertEquals(password, customer.getPassword());
        assertEquals(lastName, customer.getLastName());
        assertEquals(firstName, customer.getFirstName());
    }
}
