package ca.mcgill.ecse321.gitfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.dto.AccountCreationDto;
import ca.mcgill.ecse321.gitfit.dto.BillingInfoCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import jakarta.validation.Valid;

/**
 * This class is responsible for handling instructor account operations
 * 
 * @author Krasimir Kirov (KrasiKirov)
 */
@Service
public class CustomerAccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SportCenterService sportCenterService;

    @Autowired
    private ValidatorService validatorService;

    /**
     * Retrieve a customer by username
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @param username
     * @return Customer object
     * @throws SportCenterException if customer not found or empty username
     */
    @Transactional
    public Customer getCustomer(String username) {

        if (!StringUtils.hasText(username)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Username cannot be empty");
        }

        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        return customer;
    }

    /**
     * Retrieve all customers
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @return List of all customers
     * @throws SportCenterException if no customers found
     */
    @Transactional
    public List<Customer> getAllCustomers() {
        List<Customer> list = toList(customerRepository.findAll());
        if (list.isEmpty()) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "No current customers.");
        }

        return list;
    }

    /**
     * Create an customer. Calls the validator service to validate the input for
     * AccountCreationDto and BillingInfoCheckDto, and validates the Password.
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @param username
     * @param email
     * @param password
     * @param lastName
     * @param firstName
     * @param country
     * @param state
     * @param postalCode
     * @param cardNumber
     * @param address
     * @return Customer object in all cases and Billing object if valid non-empty
     *         billing info
     * @throws SportCenterException if password or fields required for an account
     *                              creation do not meet
     *                              the requirements, or if non-empty invalid
     *                              billing info
     */
    @Transactional
    @Valid
    public Customer createCustomer(String username, String email, String password, String lastName,
            String firstName, String country, String state, String postalCode, String cardNumber, String address) {

        validatorService.validate(new AccountCreationDto(username, email, firstName, lastName));

        if (password == null || password.trim().isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters long");
        }
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST,
                    "Password must contain at least one digit, one lowercase letter, and one uppercase letter");
        }

        Customer customer = new Customer(username, email, password, lastName, firstName,
                country, state, postalCode, cardNumber, address, sportCenterService.getSportCenter());

        if (StringUtils.hasText(country) && StringUtils.hasText(state) && StringUtils.hasText(postalCode)
                && StringUtils.hasText(cardNumber) && StringUtils.hasText(address)) {
            Billing billing = new Billing();
            billing.setCountry(country);
            billing.setState(state);
            billing.setPostalCode(postalCode);
            billing.setCardNumber(cardNumber);
            billing.setAddress(address);
            customer.setBilling(billing);
            validatorService.validate(new BillingInfoCheckDto(country, state, postalCode, cardNumber, address));
        }
        customerRepository.save(customer);
        return customer;
    }

    /**
     * Update a customer's password, by first validating it.
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @param username
     * @param newPassword
     * @return Customer object if updated
     * @throws SportCenterException if password does not meet the requirements.
     */
    @Transactional
    public Customer updateCustomerPassword(String username, String newPassword) {
        Customer customer = getCustomer(username);

        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "New password cannot be empty");
        }
        if (newPassword.length() < 8) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "New password must be at least 8 characters long");
        }
        if (!newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST,
                    "Password must contain at least one digit, one lowercase letter, and one uppercase letter");
        }

        customer.setPassword(newPassword);
        customerRepository.save(customer);
        return customer;
    }

    /**
     * Deletes a customer
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @param username
     */
    public void deleteCustomer(String username) {
        Customer customer = getCustomer(username);
        customerRepository.delete(customer);
    }

    /**
     * Converts to List
     * 
     * @author Krasimir Kirov (KrasiKirov)
     * @param iterable
     * @return List containing all the elements from the given iterable
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}