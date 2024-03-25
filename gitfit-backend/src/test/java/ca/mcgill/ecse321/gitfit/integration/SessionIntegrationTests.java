package ca.mcgill.ecse321.gitfit.integration;

import ca.mcgill.ecse321.gitfit.dao.SessionRepository;
import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.dao.FitnessClassRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.dto.SessionDto;
import ca.mcgill.ecse321.gitfit.dto.ErrorDto;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class SessionIntegrationTests {

    @Autowired
    private TestRestTemplate client;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private SportCenterRepository sportCenterRepository;

    private final int PRICE1 = 20;
    private final Time START_TIME1 = Time.valueOf("10:00:00");
    private final Time END_TIME1 = Time.valueOf("11:00:00");
    private final Date DATE1 = Date.valueOf("2022-03-03");

    private final int PRICE2 = 30;
    private final Time START_TIME2 = Time.valueOf("11:00:00");
    private final Time END_TIME2 = Time.valueOf("12:00:00");
    private final Date DATE2 = Date.valueOf("2022-03-04");

    private final int VALID_PRICE = 10;
    private final Time VALID_START_TIME = Time.valueOf("10:00:00");
    private final Time VALID_END_TIME = Time.valueOf("11:00:00");
    private final Date VALID_DATE = Date.valueOf("2022-03-05");

    private final int INVALID_PRICE = -1;
    private final Time INVALID_START_TIME = Time.valueOf("7:00:00");
    private final Time INVALID_END_TIME = Time.valueOf("13:00:00");
    private final Date INVALID_DATE = Date.valueOf("2022-03-03");

    private final String INSTRUCTOR_USERNAME = "Bob";
    private final String FITNESS_CLASS_NAME = "Yoga";
    private final Time SPORT_CENTER_OPENING_TIME = Time.valueOf("08:00:00");
    private final Time SPORT_CENTER_CLOSING_TIME = Time.valueOf("22:00:00");

    @BeforeAll
    public void setup() {
        SportCenter sportCenter = new SportCenter();
        sportCenter.setOpeningTime(SPORT_CENTER_OPENING_TIME);
        sportCenter.setClosingTime(SPORT_CENTER_CLOSING_TIME);
        sportCenter = sportCenterRepository.save(sportCenter);

        Instructor instructor = new Instructor();
        instructor.setUsername(INSTRUCTOR_USERNAME);
        instructor.setSportCenter(sportCenter);
        instructorRepository.save(instructor);

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(FITNESS_CLASS_NAME);
        fitnessClass.setSportCenter(sportCenter);
        fitnessClassRepository.save(fitnessClass);

        Session session1 = new Session();
        session1.setPrice(PRICE1);
        session1.setStartTime(START_TIME1);
        session1.setEndTime(END_TIME1);
        session1.setDate(DATE1);
        session1.setInstructor(instructor);
        session1.setFitnessClass(fitnessClass);
        sessionRepository.save(session1);

        Session session2 = new Session();
        session2.setPrice(PRICE2);
        session2.setStartTime(START_TIME2);
        session2.setEndTime(END_TIME2);
        session2.setDate(DATE2);
        session2.setInstructor(instructor);
        session2.setFitnessClass(fitnessClass);
        sessionRepository.save(session2);
    }

    @Test
    @Order(1)
    public void testCreateInvalidSessionNegativePrice() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, VALID_START_TIME, VALID_END_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions", HttpMethod.POST, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Price must be free or positive", body.getErrors().get(0));
    }

    @Test
    @Order(2)
    public void testCreateInvalidSessionEndTimeBeforeStartTime() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, VALID_END_TIME, VALID_START_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions", HttpMethod.POST, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("End time must be after start time", body.getErrors().get(0));
    }

    @Test
    @Order(3)
    public void testCreateInvalidSessionDateBeforeToday() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, VALID_START_TIME, VALID_END_TIME, INVALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions", HttpMethod.POST, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Date must be in the future", body.getErrors().get(0));
    }

}
