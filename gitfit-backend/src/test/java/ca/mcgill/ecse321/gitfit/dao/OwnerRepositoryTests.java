package ca.mcgill.ecse321.gitfit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterEach;

import ca.mcgill.ecse321.gitfit.model.Owner;

@SpringBootTest
public class OwnerRepositoryTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @AfterEach
    public void clearDatabase() {
        ownerRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadOwner() {

        // create owner properties
        String email = "OwnerTestEmail";
        String password = "OwnertestPassword";
        String lastName = "OwnerTestLastName";
        String firstName = "OwnerTestFirstName";

        // create owner and set attributes
        Owner owner = new Owner();
        owner.setEmail(email);
        owner.setPassword(password);
        owner.setLastName(lastName);
        owner.setFirstName(firstName);

        // save owner to database
        ownerRepository.save(owner);

        // Read owner from database
        // After save
        owner = ownerRepository.findByOwnerId(owner.getOwnerId());

        // Assert that owner is not null and has correct attributes
        assertNotNull(owner);
        assertEquals(email, owner.getEmail());
        assertEquals(password, owner.getPassword());
        assertEquals(lastName, owner.getLastName());
        assertEquals(firstName, owner.getFirstName());

    }

}