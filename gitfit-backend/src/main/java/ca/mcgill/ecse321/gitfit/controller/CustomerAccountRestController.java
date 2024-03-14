package ca.mcgill.ecse321.gitfit.controller;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.service.CustomerAccountService;
import ca.mcgill.ecse321.gitfit.dto.CustomerAccountDto;
import ca.mcgill.ecse321.gitfit.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CustomerAccountRestController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping(value = { "/customer", "/customer/" })
    public CustomerAccountDto getCustomer(@PathVariable("username") String username) {
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
    public CustomerAccountDto updateCustomerPassword(@RequestBody String newPassword,
            @PathVariable("username") String username) {
        Customer customer = customerAccountService.getCustomer(username);
        customer = customerAccountService.updateCustomerPassword(username, newPassword);
        return convertToDto(customer);
    }

    @PostMapping(value = { "/customer/create", "/customer/create/" })
    public CustomerAccountDto createCustomer(@RequestBody CustomerAccountDto customerAccountDto) {
        Customer customer = customerAccountService.createCustomer(customerAccountDto.getUsername(),
                customerAccountDto.getEmail(), customerAccountDto.getPassword(), customerAccountDto.getLastName(),
                customerAccountDto.getFirstName());
        return convertToDto(customer);
    }

    private CustomerAccountDto convertToDto(Customer c) {
        return new CustomerAccountDto(c.getUsername(), c.getEmail(), c.getFirstName(), c.getLastName(),
                c.getPassword());
    }
}