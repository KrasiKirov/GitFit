package ca.mcgill.ecse321.gitfit.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

@SpringBootTest
public class TestInstructorAccountService {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private SportCenterService sportCenterService;

    @InjectMocks
    private InstructorAccountService instructorAccountService;

    private static final String USERNAME = "bob123";
    private static final String PASSWORD = "secretBob12";
    private static final String FIRST_NAME = "bob";
    private static final String LAST_NAME = "jake";
    private static final String EMAIL = "bob.jake@gmail.com";
    private static final String NEW_PASSWORD = "extraSecretBob12";
    private static final SportCenter SPORT_CENTER = new SportCenter();

    @BeforeEach
    public void setMockOutput() {
        lenient().when(instructorRepository.findInstructorByUsername(anyString()))
                .thenReturn(createInstructor(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, SPORT_CENTER));
        lenient().when(instructorRepository.save(any(Instructor.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }

    private static Instructor createInstructor(String username, String password, String firstName, String lastName,
            String email, SportCenter sportCenter) {
        Instructor instructor = new Instructor();
        instructor.setUsername(username);
        instructor.setPassword(password);
        instructor.setFirstName(firstName);
        instructor.setLastName(lastName);
        instructor.setEmail(email);
        instructor.setSportCenter(sportCenter);
        return instructor;
    }

    @Test
    public void testCreateInstructor() {

    }
}
