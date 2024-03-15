package ca.mcgill.ecse321.gitfit.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.gitfit.dao.RegistrationRepository;
import ca.mcgill.ecse321.gitfit.dao.SessionRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.Registration;
import ca.mcgill.ecse321.gitfit.model.Session;

public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CustomerAccountService customerService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SportCenterService sportCenterService;

    /**
     * Get a registration by id
     * 
     * @author : Vlad Arama (vladarama)
     * @param id
     * @return a registration object
     */
    @Transactional
    public Registration getRegistration(int id) {
        Registration registration = registrationRepository.findRegistrationById(id);
        if (registration == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Registration not found.");
        }
        return registration;
    }

    /**
     * Get all registrations
     * 
     * @author : Vlad Arama (vladarama)
     * @return a list of registration objects
     */
    @Transactional
    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = toList(registrationRepository.findAll());
        if (registrations.isEmpty()) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "No current registrations.");
        } else {
            return registrations;
        }
    }

    /**
     * Create a registration
     * 
     * @author : Vlad Arama (vladarama)
     * @param date
     * @param sessionDto
     * @param customerDto
     * @return a registration object
     */
    @Transactional
    public Registration createRegistration(Date date, int sessionId, String customerName) {
        Registration registration = new Registration();
        Session session = sessionRepository.findSessionById(sessionId);
        Customer customer = customerService.getCustomer(customerName);

        if (session == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Session not found.");
        } else if (customer == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Customer not found.");
        }

        registration.setDate(date);
        registration.setSession(session);
        registration.setCustomer(customer);
        registration.setSportCenter(sportCenterService.getSportCenter());
        registration = registrationRepository.save(registration);

        return registration;
    }

    /**
     * Delete a registration
     * 
     * @author : Vlad Arama (vladarama)
     * @param id
     * @return a boolean indicating if the registration was deleted
     */
    @Transactional
    public Boolean deleteRegistration(int id) {
        Registration registration = registrationRepository.findRegistrationById(id);
        if (registration == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Registration not found.");
        }
        registrationRepository.delete(registration);
        return true;
    }

    /**
     * toList helper method
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
