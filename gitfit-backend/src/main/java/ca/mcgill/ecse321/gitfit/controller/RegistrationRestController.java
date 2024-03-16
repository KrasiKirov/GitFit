package ca.mcgill.ecse321.gitfit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gitfit.dto.RegistrationRequestDto;
import ca.mcgill.ecse321.gitfit.dto.CustomerAccountDto;
import ca.mcgill.ecse321.gitfit.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.Registration;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.service.RegistrationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * Get all registrations
     * 
     * @author : Vlad Arama (vladarama)
     * @return a list of registration DTOs
     */
    @GetMapping(value = { "/registrations", "/registrations/" })
    public List<RegistrationResponseDto> getAllRegistrations() {
        List<RegistrationResponseDto> registrationDtos = new ArrayList<>();
        for (Registration registration : registrationService.getAllRegistrations()) {
            registrationDtos.add(registrationConvertToDto(registration));
        }
        return registrationDtos;
    }

    /**
     * Get a registration by id
     * 
     * @author : Vlad Arama (vladarama)
     * @param id
     * @return a registration DTO
     */
    @GetMapping(value = { "/registrations/{id}", "/registrations/{id}/" })
    public RegistrationResponseDto getRegistrationById(@PathVariable("id") int id) {
        return registrationConvertToDto(registrationService.getRegistration(id));
    }

    /**
     * Get all registrations by customer
     * 
     * @author : Vlad Arama (vladarama)
     * @param customer
     * @return a list of registration DTOs linked to the given customer
     */
    @GetMapping(value = { "/registrations/customer", "/registrations/customer/" })
    public List<RegistrationResponseDto> getAllRegistrationsByCustomer(@RequestBody CustomerAccountDto customer) {
        List<RegistrationResponseDto> registrationDtos = new ArrayList<>();
        for (Registration registration : registrationService.getAllCustomerRegistrations(customer.getUsername())) {
            registrationDtos.add(registrationConvertToDto(registration));
        }
        return registrationDtos;
    }

    /**
     * Get all registrations by session
     * 
     * @author : Vlad Arama (vladarama)
     * @param session
     * @return a list of registration DTOs linked to the given session
     */
    @GetMapping(value = { "/registrations/session", "/registrations/session/" })
    public List<RegistrationResponseDto> getAllRegistrationsBySession(@RequestBody Session session) {
        List<RegistrationResponseDto> registrationDtos = new ArrayList<>();
        for (Registration registration : registrationService.getAllSessionRegistrations(session.getId())) {
            registrationDtos.add(registrationConvertToDto(registration));
        }
        return registrationDtos;
    }

    /**
     * Delete a registration by id
     * 
     * @author : Vlad Arama (vladarama)
     * @param id
     */
    @DeleteMapping(value = { "/registrations/{id}", "/registrations/{id}/" })
    public void deleteRegistration(@PathVariable("id") int id) {
        registrationService.deleteRegistration(id);
    }

    /**
     * Create a registration
     * 
     * @author : Vlad Arama (vladarama)
     * @param request
     * @return a registration DTO
     */
    @PostMapping(value = { "/registrations", "/registrations/" })
    public RegistrationResponseDto createRegistration(@RequestBody RegistrationRequestDto request) {
        Registration registration = registrationService.createRegistration(request.getDate(), request.getSessionId(),
                request.getCustomerName());
        return registrationConvertToDto(registration);
    }

    /**
     * Convert a registration to a DTO
     * 
     * @author : Vlad Arama (vladarama)
     * @param registration
     * @return
     */
    private RegistrationResponseDto registrationConvertToDto(Registration registration) {
        if (registration == null) {
            throw new IllegalArgumentException("There is no such registration!");
        }
        RegistrationResponseDto registrationDto = new RegistrationResponseDto(registration.getId(),
                registration.getDate(),
                registration.getSession(), customerConvertToDto(registration.getCustomer()));

        return registrationDto;
    }

    private CustomerAccountDto customerConvertToDto(Customer c) {
        return new CustomerAccountDto(c.getUsername(), c.getEmail(), c.getFirstName(), c.getLastName(),
                c.getPassword());
    }

}
