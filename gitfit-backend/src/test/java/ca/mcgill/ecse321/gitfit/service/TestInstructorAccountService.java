package ca.mcgill.ecse321.gitfit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.dto.AccountCreationDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

@SpringBootTest
public class TestInstructorAccountService {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private SportCenterService sportCenterService;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private InstructorAccountService instructorAccountService;

    @Test
    public void testCreateInstructor() {
        SportCenter sportCenter = new SportCenter();

        // Create instructor object params
        String username = "Obiwan123";
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        // mock behaviours
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        Instructor createdInstructor = instructorAccountService.createInstructor(username, email, password, lastName,
                firstName);

        // assertions
        assertNotNull(createdInstructor);
        assertEquals(username, createdInstructor.getUsername());
        assertEquals(password, createdInstructor.getPassword());
        assertEquals(email, createdInstructor.getEmail());
        assertEquals(firstName, createdInstructor.getFirstName());
        assertEquals(lastName, createdInstructor.getLastName());
        assertEquals(sportCenter, createdInstructor.getSportCenter());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    public void testCreateInstructorInvalidUsername() {
        SportCenter sportCenter = new SportCenter();

        // Create instructor object params
        String username = null;
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        // mock behaviours
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
        doThrow(new SportCenterException(HttpStatus.BAD_REQUEST, "Username cannot be null")).when(validatorService)
                .validate(any(AccountCreationDto.class));

        Instructor createdInstructor = null;
        try {
            createdInstructor = instructorAccountService.createInstructor(username, email, password, lastName,
                    firstName);
        } catch (SportCenterException e) {
            assertEquals("Username cannot be null", e.getMessage());
        }

        assertNull(createdInstructor);
        verify(instructorRepository, times(0)).save(any(Instructor.class));
    }

    @Test
    public void testCreateInstructorInvalidEmail() {
        SportCenter sportCenter = new SportCenter();

        // Create instructor object params
        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        // mock behaviours
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
        doThrow(new SportCenterException(HttpStatus.BAD_REQUEST, "Email must end with @ (domain) .com"))
                .when(validatorService)
                .validate(any(AccountCreationDto.class));

        Instructor createdInstructor = null;
        try {
            createdInstructor = instructorAccountService.createInstructor(username, email, password, lastName,
                    firstName);
        } catch (SportCenterException e) {
            assertEquals("Email must end with @ (domain) .com", e.getMessage());
        }

        assertNull(createdInstructor);
        verify(instructorRepository, times(0)).save(any(Instructor.class));
    }

    @Test
    public void testCreateInstructorInvalidPassword() {
        SportCenter sportCenter = new SportCenter();

        // Create instructor object params
        String username = "obiwan123";
        String password = "MissingDigit";
        String email = "obiwan.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        // mock behaviours
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
        doThrow(new SportCenterException(HttpStatus.BAD_REQUEST,
                "Password must contain at least one digit, one lowercase letter, and one uppercase letter"))
                .when(validatorService)
                .validate(any(AccountCreationDto.class));

        Instructor createdInstructor = null;
        try {
            createdInstructor = instructorAccountService.createInstructor(username, email, password, lastName,
                    firstName);
        } catch (SportCenterException e) {
            assertEquals("Password must contain at least one digit, one lowercase letter, and one uppercase letter",
                    e.getMessage());
        }

        assertNull(createdInstructor);
        verify(instructorRepository, times(0)).save(any(Instructor.class));
    }

    @Test
    public void testGetInstructor() {
        SportCenter sportCenter = new SportCenter();

        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);

        Instructor instructor = instructorAccountService.createInstructor(username, email, password, lastName,
                firstName);

        when(instructorRepository.findInstructorByUsername(username)).thenReturn(instructor);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        Instructor retrievedInstructor = instructorAccountService.getInstructor(username);

        assertNotNull(retrievedInstructor);
        assertEquals(username, retrievedInstructor.getUsername());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    public void testGetUnknownInstructor() {
        String unknownUsername = "unknown";

        when(instructorRepository.findInstructorByUsername(unknownUsername)).thenReturn(null);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        Instructor retrievedInstructor = null;
        try {
            retrievedInstructor = instructorAccountService.getInstructor(unknownUsername);
        } catch (SportCenterException e) {
            assertEquals("Instructor not found.", e.getMessage());
        }

        assertNull(retrievedInstructor);
        verify(instructorRepository, never()).save(any(Instructor.class));
    }

    @Test
    public void testGetAllInstructors() {
        SportCenter sportCenter = new SportCenter();

        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";

        Instructor instructor1 = new Instructor();
        instructor1.setUsername(username);
        instructor1.setEmail(email);
        instructor1.setPassword(password);
        instructor1.setFirstName(firstName);
        instructor1.setLastName(lastName);
        instructor1.setSportCenter(sportCenter);

        String username2 = "yoda";
        String password2 = "extraSecret";
        String email2 = "yoda@starWars.com";
        String firstName2 = "Yoda";
        String lastName2 = "Master";

        Instructor instructor2 = new Instructor();
        instructor2.setUsername(username2);
        instructor2.setEmail(email2);
        instructor2.setPassword(password2);
        instructor2.setFirstName(firstName2);
        instructor2.setLastName(lastName2);
        instructor2.setSportCenter(sportCenter);

        when(instructorRepository.findAll()).thenReturn(Arrays.asList(instructor1, instructor2));
        List<Instructor> retrievedInstructors = instructorAccountService.getAllInstructors();

        assertNotNull(retrievedInstructors);
        assertEquals(2, retrievedInstructors.size());
        assertTrue(retrievedInstructors.contains(instructor1));
        assertTrue(retrievedInstructors.contains(instructor2));
        verify(instructorRepository, never()).save(any(Instructor.class));
    }

    @Test
    public void testGetAllInstructorsEmpty() {
        when(instructorRepository.findAll()).thenReturn(Arrays.asList());
        List<Instructor> retrievedInstructors = null;
        try {
            retrievedInstructors = instructorAccountService.getAllInstructors();
        } catch (SportCenterException e) {
            assertEquals("No current instructors.", e.getMessage());
        }

        assertNull(retrievedInstructors);
        verify(instructorRepository, never()).save(any(Instructor.class));
    }

    @Test
    public void testUpdateInstructorPassword() {
        SportCenter sportCenter = new SportCenter();

        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";
        Instructor instructor1 = new Instructor();
        instructor1.setUsername(username);
        instructor1.setEmail(email);
        instructor1.setPassword(password);
        instructor1.setFirstName(firstName);
        instructor1.setLastName(lastName);
        instructor1.setSportCenter(sportCenter);

        when(instructorRepository.findInstructorByUsername(username)).thenReturn(instructor1);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });

        String newPassword = "NewPassword123";

        Instructor updatedInstructor = instructorAccountService.updateInstructorPassword(username, newPassword);
        assertNotNull(updatedInstructor);
        assertEquals(newPassword, updatedInstructor.getPassword());
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    public void testUpdateInstructorPasswordInvalid() {
        SportCenter sportCenter = new SportCenter();

        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";
        Instructor instructor1 = new Instructor();
        instructor1.setUsername(username);
        instructor1.setEmail(email);
        instructor1.setPassword(password);
        instructor1.setFirstName(firstName);
        instructor1.setLastName(lastName);
        instructor1.setSportCenter(sportCenter);

        when(instructorRepository.findInstructorByUsername(username)).thenReturn(instructor1);
        when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
        doThrow(new SportCenterException(HttpStatus.BAD_REQUEST,
                "Password cannot be null"))
                .when(validatorService)
                .validate(any(PasswordCheckDto.class));

        String newPassword = "";
        try {
            instructorAccountService.updateInstructorPassword(username, newPassword);
        } catch (SportCenterException e) {
            assertEquals("Password cannot be null", e.getMessage());
        }

        assertEquals(password, instructor1.getPassword());
        verify(instructorRepository, never()).save(any(Instructor.class));

    }

    @Test
    public void testDeleteInstructor() {
        SportCenter sportCenter = new SportCenter();

        String username = "obiwan123";
        String password = "secret";
        String email = "obiwan@starwars.com";
        String firstName = "Obiwan";
        String lastName = "Kenobi";
        Instructor instructor1 = new Instructor();
        instructor1.setUsername(username);
        instructor1.setEmail(email);
        instructor1.setPassword(password);
        instructor1.setFirstName(firstName);
        instructor1.setLastName(lastName);
        instructor1.setSportCenter(sportCenter);

        when(instructorRepository.findInstructorByUsername(username)).thenReturn(instructor1);

        instructorAccountService.deleteInstructor(username);

        verify(instructorRepository, times(1)).delete(instructor1);
    }
}
