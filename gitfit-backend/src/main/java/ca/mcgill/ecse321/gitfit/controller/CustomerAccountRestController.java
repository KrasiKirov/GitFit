package ca.mcgill.ecse321.gitfit.controller;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.service.CustomerAccountService;
import ca.mcgill.ecse321.gitfit.dto.CustomerAccountDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordRequestDto;
import ca.mcgill.ecse321.gitfit.dto.CustomerAccountRequestDto;
import ca.mcgill.ecse321.gitfit.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class CustomerAccountRestController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping(value = { "/customer", "/customer/" })
    public CustomerAccountDto getCustomer(@RequestBody String username) {
        Customer customer = customerAccountService.getCustomer(username);
        return convertToDto(customer);
    }

    @GetMapping(value = { "/customer/all", "/customer/all/" })
    public List<CustomerAccountDto> getAllCustomers() {
        List<Customer> list = customerAccountService.getAllCustomers();

        List<CustomerAccountDto> dtoList = new ArrayList<>();
        for (Customer customer : list) {
            dtoList.add(convertToDto(customer));
        }
        return dtoList;
    }

    @PutMapping(value = { "/customer/updatePassword", "/customer/updatePassword/" })
    public CustomerAccountDto updateCustomerPassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        Customer customer = customerAccountService.getCustomer(passwordRequestDto.getUsername());
        customer = customerAccountService.updateCustomerPassword(passwordRequestDto.getUsername(),
                passwordRequestDto.getPassword());
        return convertToDto(customer);
    }

    @PostMapping(value = { "/customer/create", "/customer/create/" })
    public CustomerAccountDto createCustomer(@RequestBody CustomerAccountRequestDto customerAccountRequestDto) {
        Customer customer = customerAccountService.createCustomer(customerAccountRequestDto.getUsername(),
                customerAccountRequestDto.getEmail(), customerAccountRequestDto.getPassword(),
                customerAccountRequestDto.getLastName(), customerAccountRequestDto.getFirstName(),
                customerAccountRequestDto.getCountry(), customerAccountRequestDto.getState(),
                customerAccountRequestDto.getPostalCode(), customerAccountRequestDto.getCardNumber(),
                customerAccountRequestDto.getAddress());
        return convertToDto(customer);
    }

    @DeleteMapping(value = { "/customer/delete", "/customer/delete/" })
    public void deleteCustomer(@RequestBody String username) {
        customerAccountService.deleteCustomer(username);
    }

    private CustomerAccountDto convertToDto(Customer customer) {
        return new CustomerAccountDto(customer.getUsername(), customer.getEmail(), customer.getFirstName(),
                customer.getLastName(),
                customer.getPassword());
    }
}