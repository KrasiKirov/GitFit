package ca.mcgill.ecse321.gitfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.dto.AccountCreationDto;
import ca.mcgill.ecse321.gitfit.dto.BillingInfoCheckDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Customer;

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
        } else {
            return list;
        }
    }

    @Transactional
    public Customer createCustomer(String username, String email, String password, String lastName,
            String firstName) {

        validatorService.validate(new AccountCreationDto());
        validatorService.validate(new BillingInfoCheckDto());
        validatorService.validate(new PasswordCheckDto(password));

        Customer customer = new Customer(username, email, password, lastName, firstName,
                sportCenterService.getSportCenter());
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public Customer updateCustomerPassword(String username, String newPassword) {
        Customer customer = customerRepository.findCustomerByUsername(username);

        validatorService.validate(new PasswordCheckDto(newPassword));

        customer.setPassword(newPassword);
        customerRepository.save(customer);
        return customer;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}