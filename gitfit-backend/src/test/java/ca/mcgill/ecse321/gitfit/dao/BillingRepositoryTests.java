package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.repository.BillingRepository;
import ca.mcgill.ecse321.gitfit.repository.CustomerRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillingRepositoryTests {
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        billingRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testBillingPersistence() {

        Customer customer = new Customer();
        customer = customerRepository.save(customer);

        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "McGill Avenue";
        Billing billing = new Billing(country, state, postalCode, cardNumber, address, customer);
        billing = billingRepository.save(billing);

        //        billing.setCountry("Canada");
//        billing.setCustomer(customer);


        // getId from saved object
        int billingId = billing.getId();
        int customerId = customer.getCustomerId();

        // read back object from database
        Billing billingFromDB = billingRepository.findBillingById(billingId);

        // assertions
        assertEquals(billingId, billingFromDB.getId());
        assertEquals(country, billingFromDB.getCountry());
        assertEquals(state, billingFromDB.getState());
        assertEquals(postalCode, billingFromDB.getPostalCode());
        assertEquals(cardNumber, billingFromDB.getCardNumber());
        assertEquals(address, billingFromDB.getAddress());
        assertEquals(customerId, customer.getCustomerId());

//        assertNotNull(billing);
//        assertEquals("Canada", billingRepository.findBillingById(billing.getId()).getCountry());
        System.out.println(customerId + billingId);
    }


}
