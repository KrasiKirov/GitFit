package ca.mcgill.ecse321.gitfit.repository;

import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
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
public class BillingTest {
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
//        Billing billing = new Billing("Canada","Quebec", "H3A 0G4", "8888 8888 8888 8888", "McGill Avenue",)
        Customer customer = new Customer();
        customerRepository.save(customer);
        Billing billing = new Billing();
        billing.setCountry("Canada");
        billing.setCustomer(customer);
        billing = billingRepository.save(billing);

        assertNotNull(billing);
        assertEquals("Canada", billingRepository.findBillingById(billing.getId()).getCountry());
        System.out.println(billingRepository.findBillingById(billing.getId()).getCountry());


    }


}
