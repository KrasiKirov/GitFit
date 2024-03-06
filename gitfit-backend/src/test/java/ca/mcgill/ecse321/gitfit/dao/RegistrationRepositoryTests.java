package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.model.Customer;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Registration;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegistrationRepositoryTests {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @AfterEach
    public void clearDatabase() {
        registrationRepository.deleteAll();
        sessionRepository.deleteAll();
        customerRepository.deleteAll();
        fitnessClassRepository.deleteAll();
        instructorRepository.deleteAll();
    }

    @Test
    public void testSessionPersistence() {
        // create customer
        Customer customer = new Customer();
        customerRepository.save(customer);

        // create instructor and fitness class
        Instructor instructor = new Instructor();
        instructor = instructorRepository.save(instructor);
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass = fitnessClassRepository.save(fitnessClass);

        // create session
        Session session = new Session();
        session.setInstructor(instructor);
        session.setFitnessClass(fitnessClass);
        sessionRepository.save(session);

        // create registration
        Registration registration = new Registration();
        Date aDate = Date.valueOf("2024-02-01");
        registration.setDate(aDate);
        registration.setSession(session);
        registration.setCustomer(customer);
        registrationRepository.save(registration);

        // getId from saved object
        int sessionId = session.getId();
        int customerId = customer.getId();
        int registartionId = registration.getId();

        // read from database
        Registration registrationFromDb = registrationRepository.findRegistrationById(registartionId);

        // check if the object from the database is the same as the object that was saved
        assertNotNull(registrationFromDb);
        assertNotNull(registrationFromDb.getId());

        assertEquals(aDate, registrationFromDb.getDate());
        assertEquals(sessionId, registrationFromDb.getSession().getId());
        assertEquals(customerId, registrationFromDb.getCustomer().getId());
    }
}
