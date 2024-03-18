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
import ca.mcgill.ecse321.gitfit.dto.PasswordChangeDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import jakarta.validation.Valid;

@Service
public class CustomerAccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SportCenterService sportCenterService;

    @Autowired
    private ValidatorService validatorService;

    @Transactional
    public Customer getCustomer(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        return customer;
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        List<Customer> list = toList(customerRepository.findAll());
        if (list.isEmpty()) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "No current customers.");
        }

        return list;
    }

    @Transactional
    @Valid
    public Customer createCustomer(String username, String email, String password, String lastName,
            String firstName, String country, String state, String postalCode, String cardNumber, String address) {

        validatorService.validate(new AccountCreationDto(username, email, firstName, lastName));
        validatorService.validate(new PasswordCheckDto(password));

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

    @Transactional
    public Customer updateCustomerPassword(String username, String newPassword) {
        Customer customer = getCustomer(username);

        validatorService.validate(new PasswordChangeDto(newPassword));

        customer.setPassword(newPassword);
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(String username) {
        Customer customer = getCustomer(username);
        customerRepository.delete(customer);
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}