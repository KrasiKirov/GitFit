package ca.mcgill.ecse321.gitfit.integration;

import ca.mcgill.ecse321.gitfit.dao.BillingRepository;
import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.dto.BillingRequestDto;
import ca.mcgill.ecse321.gitfit.dto.BillingResponseDto;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BillingIntegrationTests {
    @Autowired
    private TestRestTemplate client;
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SportCenterRepository sportCenterRepository;

    private final String VALID_COUNTRY = "Canada";
    private final String VALID_STATE = "Quebec";
    private final String VALID_POSTAL_CODE = "H3H 1P3";
    private final String VALID_CARD_NUMBER = "8888 8888 8888 8888";
    private final String VALID_ADDRESS = "1234 Rue Sherbrooke";
    private final String VALID_USERNAME = "Bob";

//    @BeforeAll
//    @AfterAll
//    public void cleanDatabase() {
//        billingRepository.deleteAll();
//        customerRepository.deleteAll();
//        sportCenterRepository.deleteAll();
//    }

    public String setUpCustomer() {
        SportCenter sportCenter = new SportCenter();
        sportCenter = sportCenterRepository.save(sportCenter);
        Customer customer = new Customer();
        customer.setUsername(VALID_USERNAME);
        customer.setSportCenter(sportCenter);
        customer = customerRepository.save(customer);
        return customer.getUsername();
    }

    @Test
    @Order(1)
    public void testCreateBilling() {
        String username = setUpCustomer();
        BillingRequestDto billingRequestDto = new BillingRequestDto(VALID_COUNTRY, VALID_STATE, VALID_POSTAL_CODE, VALID_CARD_NUMBER, VALID_ADDRESS, username);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BillingRequestDto> entity = new HttpEntity<>(billingRequestDto, headers);
        ResponseEntity<BillingResponseDto> response = client.exchange("/customers/" + username + "/billing", HttpMethod.PUT, entity, BillingResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BillingResponseDto createdBilling = response.getBody();
        assertNotNull(createdBilling);
        assertEquals(VALID_COUNTRY, createdBilling.getCountry());
        assertEquals(VALID_STATE, createdBilling.getState());
        assertEquals(VALID_POSTAL_CODE, createdBilling.getPostalCode());
        assertEquals("8888", createdBilling.getCardNumberEnd());
    }

    @Test
    @Order(2)
    public void testGetBilling() {
//        String username = setUpCustomer();
//        BillingRequestDto billingRequestDto = new BillingRequestDto(VALID_COUNTRY, VALID_STATE, VALID_POSTAL_CODE, VALID_CARD_NUMBER, VALID_ADDRESS, username);
//      HttpHeaders headers = new HttpHeaders();    HttpEntity<BillingRequestDto> entity = new HttpEntity<>(billingRequestDto, headers);
//        Billing billing = billingRepository.findAll().iterator().next();
//        System.out.println(billing.getCountry());

        ResponseEntity<BillingResponseDto> response = client.getForEntity("/customers/" + VALID_USERNAME + "/billing", BillingResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        BillingResponseDto createdBilling = response.getBody();
        assertNotNull(createdBilling);
        assertEquals(VALID_COUNTRY, createdBilling.getCountry());
        assertEquals(VALID_STATE, createdBilling.getState());
        assertEquals(VALID_POSTAL_CODE, createdBilling.getPostalCode());
        assertEquals("8888", createdBilling.getCardNumberEnd());
    }

    @Test
    @Order(3)
    public void testDeleteBilling() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BillingRequestDto> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = client.exchange("/customers/" + VALID_USERNAME + "/billing", HttpMethod.DELETE, entity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
