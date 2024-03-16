package ca.mcgill.ecse321.gitfit.service;

import ca.mcgill.ecse321.gitfit.dao.BillingRepository;
import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillingService {
    @Autowired
    BillingRepository  billingRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Transactional
    public Billing createBilling(String country, String state, String postalCode, String cardNumber, String address, String username) {
        String error = "";

        //input validation
        if (country==null || state==null || postalCode==null || cardNumber==null || address==null ||country=="" || state=="" || postalCode=="" || cardNumber=="" || address=="") {
            error = "The billing information fields must be completed.";
        }
        if (error.length()!=0) {
            throw new IllegalArgumentException(error);
        }
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new IllegalArgumentException("The customer does not exist.");
        }
        Billing checkExistingBilling = billingRepository.findBillingByCustomer(customer);
        if (checkExistingBilling!=null) {
            throw new IllegalArgumentException("The customer has an existing billing set up.");
        }
        Billing billing = new Billing(country,state,postalCode,cardNumber,address, customer);
        return billingRepository.save(billing);
    }

    @Transactional
    public Billing updateBilling(String country, String state, String postalCode, String cardNumber, String address,String username) {
        String error = "";

        //input validation
        if (country==null || state==null || postalCode==null || cardNumber==null || address==null ||country=="" || state=="" || postalCode=="" || cardNumber=="" || address=="") {
            throw new IllegalArgumentException("The billing information fields must be completed.");
        }
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new IllegalArgumentException("The customer does not exist.");
        }
        Billing billing = billingRepository.findBillingByCustomer(customer);
        if (billing==null) {
            throw new IllegalArgumentException("The customer does not have billing set up.");
        }

        billing.setCountry(country);
        billing.setState(state);
        billing.setPostalCode(postalCode);
        billing.setCardNumber(cardNumber);
        billing.setAddress(address);
        return billingRepository.save(billing);
    }

    @Transactional
    public Billing getBilling(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new IllegalArgumentException("The customer does not exist.");
        }
        Billing billing = billingRepository.findBillingByCustomer(customer);
        if (billing==null) {
            throw new IllegalArgumentException("The customer does not have billing set up.");
        }
        return billing;
    }

    @Transactional
    public void deleteBilling(String username){
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new IllegalArgumentException("The customer does not exist.");
        }
        Billing billing = billingRepository.findBillingByCustomer(customer);
        if (billing==null) {
            throw new IllegalArgumentException("The customer does not have billing set up.");
        }
        billingRepository.deleteById(billing.getId());
    }

}
