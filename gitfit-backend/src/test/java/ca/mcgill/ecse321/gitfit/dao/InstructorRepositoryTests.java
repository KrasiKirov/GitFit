package ca.mcgill.ecse321.gitfit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;

import ca.mcgill.ecse321.gitfit.model.Instructor;

@SpringBootTest
public class InstructorRepositoryTests {

    @Autowired
    private InstructorRepository instructorRepository;

    @AfterEach
    public void clearDatabase() {
        instructorRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadInstructor() {

        // create instructor properties
        String email = "InstrucorTestEmail";
        String password = "InstructortestPassword";
        String lastName = "InstructorTestLastName";
        String firstName = "InstrucorTestFirstName";

        // create instructor and set attributes
        Instructor instructor = new Instructor();
        instructor.setEmail(email);
        instructor.setPassword(password);
        instructor.setLastName(lastName);
        instructor.setFirstName(firstName);

        // save instructor to database, store persisted instructor in variable
        instructor = instructorRepository.save(instructor);

        // Read instructor from database
        // After save
        instructor = instructorRepository.findInstructorByUsername(instructor.getUsername());

        // Assert that instructor is not null and has correct attributes
        assertNotNull(instructor);
        assertEquals(email, instructor.getEmail());
        assertEquals(password, instructor.getPassword());
        assertEquals(lastName, instructor.getLastName());
        assertEquals(firstName, instructor.getFirstName());

    }

}
