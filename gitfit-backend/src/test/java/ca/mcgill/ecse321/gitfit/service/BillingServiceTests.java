package ca.mcgill.ecse321.gitfit.service;

import ca.mcgill.ecse321.gitfit.dao.BillingRepository;
import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BillingServiceTests {



    @Mock
    private BillingRepository billingRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private SportCenterRepository sportCenterRepository;

    @InjectMocks
    private BillingService billingService;

    @Test
    public void createBillingTest(){
        SportCenter sportCenter = new SportCenter();

        Customer customer = new Customer();
        customer.setUsername("Bob");
        customer.setSportCenter(sportCenter);

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing();
        billing1.setCountry(country);
        billing1.setState(state);
        billing1.setPostalCode(postalCode);
        billing1.setCardNumber(cardNumber);
        billing1.setAddress(address);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = billingService.createBilling(country,state,postalCode,cardNumber,address,"Bob");

        // assertions
        assertNotNull(createdBilling);
        assertEquals(country, createdBilling.getCountry());
        assertEquals(state, createdBilling.getState());
        assertEquals(postalCode,createdBilling.getPostalCode());
        assertEquals(cardNumber,createdBilling.getCardNumber());
        assertEquals(address,createdBilling.getAddress());
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    public void createExistingBillingTest() {
        // create customer object for billing
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing();
        Billing billing2 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(billing1);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing2);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.createBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The customer has an existing billing set up.");
    }

    @Test
    public void createNonExistingCustomerBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(null);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.createBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The customer does not exist.");
    }


    @Test
    public void createIncompleteFieldBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.createBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The billing information fields must be completed.");
    }

    @Test
    public void updateBillingTest(){
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);
        String updatedState = "Ontario";

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(billing1);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = billingService.updateBilling(country,updatedState,postalCode,cardNumber,address,"Bob");

        // assertions
        assertNotNull(createdBilling);
        assertEquals(country, createdBilling.getCountry());
        assertEquals(updatedState, createdBilling.getState());
        assertEquals(postalCode,createdBilling.getPostalCode());
        assertEquals(cardNumber,createdBilling.getCardNumber());
        assertEquals(address,createdBilling.getAddress());
        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    public void updateIncompleteFieldBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(billing1);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.updateBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The billing information fields must be completed.");
    }


    @Test
    public void updateNonExistingCustomerBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(null);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(billing1);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.updateBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The customer does not exist.");
    }

    @Test
    public void updateNonExistingBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(null);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing1);

        // use the service
        Billing createdBilling = null;
        String error = null;
        try {
            createdBilling = billingService.updateBilling(country,state,postalCode,cardNumber,address,"Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertNull(createdBilling);
        assertEquals(error, "The customer does not have billing set up.");
    }

    @Test
    public void deleteBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);
        billing1.setId(1);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(billing1);

        billingService.deleteBilling("Bob");

        verify(billingRepository, times(1)).deleteById(any(Integer.class));
    }
    @Test
    public void deleteNonExistingCustomerBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(null);

        // use the service
        String error = null;
        try {
            billingService.deleteBilling("Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertEquals(error, "The customer does not exist.");
    }

    @Test
    public void deleteNonExistingBillingTest() {
        Customer customer = new Customer();
        customer.setUsername("Bob");

        // create billing object
        String country = "Canada";
        String state = "Quebec";
        String postalCode = "H3A 0G4";
        String cardNumber = "8888 8888 8888 8888";
        String address = "666 McGill Avenue";
        Billing billing1 = new Billing(country,state,postalCode,cardNumber,address, customer);

        // mock behaviours
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(customer);
        when(billingRepository.findBillingByCustomer(any(Customer.class))).thenReturn(null);

        // use the service
        String error = null;
        try {
            billingService.deleteBilling("Bob");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // assertions
        assertEquals(error, "The customer does not have billing set up.");
    }

}
