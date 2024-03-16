package ca.mcgill.ecse321.gitfit.integration;

import ca.mcgill.ecse321.gitfit.dao.BillingRepository;
import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.dto.BillingRequestDto;
import ca.mcgill.ecse321.gitfit.dto.BillingResponseDto;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @BeforeEach
    @AfterEach
    public void cleanDatabase() {
        billingRepository.deleteAll();
        customerRepository.deleteAll();
        sportCenterRepository.deleteAll();
    }

    public String setUpCustomer() {
        SportCenter sportCenter = new SportCenter();
        sportCenter = sportCenterRepository.save(sportCenter);
        Customer customer = new Customer();
        customer.setUsername("Bob");
        customer.setSportCenter(sportCenter);
        customer = customerRepository.save(customer);
        return customer.getUsername();
    }

    @Test
    public void testCreateBilling() {
        String username = setUpCustomer();
        BillingRequestDto billingRequestDto = new BillingRequestDto(VALID_COUNTRY, VALID_STATE, VALID_POSTAL_CODE, VALID_CARD_NUMBER, VALID_ADDRESS, username);

//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<BillingRequestDto> entity = new HttpEntity<>(billingRequestDto, headers);
//        ResponseEntity<BillingResponseDto> response = client.exchange("/customers/" + username + "/billing", HttpMethod.POST, entity, BillingResponseDto.class);
        System.out.println("/customers/" + username + "/billing");

        ResponseEntity<BillingResponseDto> response = client.postForEntity("/customers/" + username + "/billing", billingRequestDto, BillingResponseDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        BillingResponseDto createdBilling = response.getBody();
        assertNotNull(createdBilling);
        assertEquals(VALID_COUNTRY, createdBilling.getCountry());
        assertEquals(VALID_STATE, createdBilling.getState());
        assertEquals(VALID_POSTAL_CODE, createdBilling.getPostalCode());
        assertEquals("8888", createdBilling.getCardNumberEnd());


    }

}
