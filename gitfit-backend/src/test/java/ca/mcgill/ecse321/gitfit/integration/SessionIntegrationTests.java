package ca.mcgill.ecse321.gitfit.integration;

import ca.mcgill.ecse321.gitfit.dao.SessionRepository;
import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.dao.FitnessClassRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.dto.SessionDto;
import ca.mcgill.ecse321.gitfit.dto.DatesDto;
import ca.mcgill.ecse321.gitfit.dto.ErrorDto;
import ca.mcgill.ecse321.gitfit.dto.HoursDto;
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

    private final int ID1 = 1;
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

    private final String INSTRUCTOR_USERNAME = "Jimmy";
    private final String FITNESS_CLASS_NAME = "Yoga";

    private final Time SPORT_CENTER_OPENING_TIME = Time.valueOf("08:00:00");
    private final Time SPORT_CENTER_CLOSING_TIME = Time.valueOf("22:00:00");

    @BeforeAll
    public void setup() {
        sessionRepository.deleteAll();
        instructorRepository.deleteAll();
        fitnessClassRepository.deleteAll();
        sportCenterRepository.deleteAll();

        SportCenter sportCenter = new SportCenter();

        sportCenter.setOpeningTime(SPORT_CENTER_OPENING_TIME);
        sportCenter.setClosingTime(SPORT_CENTER_CLOSING_TIME);
        sportCenter = sportCenterRepository.save(sportCenter);

        Instructor instructor = new Instructor(INSTRUCTOR_USERNAME, "email@gmail.com", "password", "lastName",
                "firstName", sportCenter);
        instructorRepository.save(instructor);

        FitnessClass fitnessClass = new FitnessClass(FITNESS_CLASS_NAME, "description", sportCenter);
        fitnessClassRepository.save(fitnessClass);

        Session session1 = new Session(PRICE1, START_TIME1, END_TIME1, DATE1, instructor, fitnessClass, sportCenter);
        session1.setId(ID1);
        sessionRepository.save(session1);

        Session session2 = new Session(PRICE2, START_TIME2, END_TIME2, DATE2, instructor, fitnessClass, sportCenter);
        sessionRepository.save(session2);
    }

    @AfterAll
    public void clearDatabase() {
        sessionRepository.deleteAll();
        instructorRepository.deleteAll();
        fitnessClassRepository.deleteAll();
        sportCenterRepository.deleteAll();
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

    @Test
    @Order(4)
    public void testCreateInvalidSessionSportCenterNotOpen() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, INVALID_START_TIME, VALID_END_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions", HttpMethod.POST, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Time must be within sport center hours", body.getErrors().get(0));
    }

    @Test
    @Order(5)
    public void testCreateInvalidSessionSlotTaken() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, START_TIME1, VALID_END_TIME, DATE1,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions", HttpMethod.POST, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Time slot is already taken", body.getErrors().get(0));
    }

    @Test
    @Order(6)
    public void testCreateValidSession() {
        SessionDto sessionDto = new SessionDto(VALID_PRICE, VALID_START_TIME, VALID_END_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<SessionDto> response = client.exchange("/sessions", HttpMethod.POST, entity, SessionDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto createdSession = response.getBody();
        assertNotNull(createdSession);
        assertEquals(VALID_PRICE, createdSession.getPrice());
        assertEquals(VALID_START_TIME, createdSession.getStartTime());
        assertEquals(VALID_END_TIME, createdSession.getEndTime());
        assertEquals(VALID_DATE, createdSession.getDate());
        assertEquals(INSTRUCTOR_USERNAME, createdSession.getInstructorUsername());
        assertEquals(FITNESS_CLASS_NAME, createdSession.getFitnessClassName());
    }

    @Test
    @Order(7)
    public void testGetAllSessions() {
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions", HttpMethod.GET, null,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(8)
    public void testGetSessionById() {
        ResponseEntity<SessionDto> response = client.exchange("/sessions/" + ID1, HttpMethod.GET, null,
                SessionDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto session = response.getBody();
        assertNotNull(session);
        assertEquals(PRICE1, session.getPrice());
        assertEquals(START_TIME1, session.getStartTime());
        assertEquals(END_TIME1, session.getEndTime());
        assertEquals(DATE1, session.getDate());
        assertEquals(INSTRUCTOR_USERNAME, session.getInstructorUsername());
        assertEquals(FITNESS_CLASS_NAME, session.getFitnessClassName());
    }

    @Test
    @Order(9)
    public void testGetSessionsByInstructor() {
        HttpEntity<String> entity = new HttpEntity<>(INSTRUCTOR_USERNAME);
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-instructor", HttpMethod.GET, entity,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(10)
    public void testGetSessionsByFitnessClass() {
        HttpEntity<String> entity = new HttpEntity<>(FITNESS_CLASS_NAME);
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-fitness-class", HttpMethod.GET, entity,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(11)
    public void testGetSessionsByInstructorAndFitnessClass() {
        HttpEntity<SessionDto> entity = new HttpEntity<>(
                new SessionDto(0, null, null, null, INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME));
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-instructor-and-fitness-class",
                HttpMethod.GET, entity, SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(12)
    public void testGetSessionsByInvalidMaxPrice() {
        HttpEntity<Integer> entity = new HttpEntity<>(INVALID_PRICE);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions/by-max-price", HttpMethod.GET, entity,
                ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Price must be free or positive", body.getErrors().get(0));
    }

    @Test
    @Order(13)
    public void testGetSessionsByMaxPrice() {
        HttpEntity<Integer> entity = new HttpEntity<>(PRICE1);
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-max-price", HttpMethod.GET, entity,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(14)
    public void testGetSessionsByInvalidDate() {
        HttpEntity<DatesDto> entity = new HttpEntity<>(new DatesDto(DATE2, DATE1));
        ResponseEntity<ErrorDto> response = client.exchange("/sessions/by-date", HttpMethod.GET, entity,
                ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Start date must be before end date", body.getErrors().get(0));
    }

    @Test
    @Order(15)
    public void testGetSessionsByDate() {
        HttpEntity<DatesDto> entity = new HttpEntity<>(new DatesDto(DATE1, DATE2));
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-date", HttpMethod.GET, entity,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);
        assertEquals(3, sessions.length);
    }

    @Test
    @Order(16)
    public void testGetSessionsByInvalidTime() {
        HttpEntity<HoursDto> entity = new HttpEntity<>(new HoursDto(END_TIME1, START_TIME1));
        ResponseEntity<ErrorDto> response = client.exchange("/sessions/by-time", HttpMethod.GET, entity,
                ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Start time must be before end time", body.getErrors().get(0));
    }

    @Test
    @Order(17)
    public void testGetSessionsByTime() {
        HttpEntity<HoursDto> entity = new HttpEntity<>(new HoursDto(START_TIME1, END_TIME2));
        ResponseEntity<SessionDto[]> response = client.exchange("/sessions/by-time", HttpMethod.GET, entity,
                SessionDto[].class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto[] sessions = response.getBody();
        assertNotNull(sessions);

        assertEquals(3, sessions.length);
    }

    @Test
    @Order(18)
    public void testUpdateSessionInvalidPrice() {
        SessionDto sessionDto = new SessionDto(ID1, INVALID_PRICE, VALID_START_TIME, VALID_END_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions/" + ID1, HttpMethod.PUT, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("Price must be free or positive", body.getErrors().get(0));
    }

    @Test
    @Order(19)
    public void testUpdateSessionInvalidTimes() {
        SessionDto sessionDto = new SessionDto(ID1, PRICE1, INVALID_END_TIME, INVALID_START_TIME, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<ErrorDto> response = client.exchange("/sessions/" + ID1, HttpMethod.PUT, entity, ErrorDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getErrors().size());
        assertEquals("End time must be after start time", body.getErrors().get(0));
    }

    @Test
    @Order(20)
    public void testUpdateSession() {
        SessionDto sessionDto = new SessionDto(ID1, PRICE1, START_TIME2, END_TIME2, VALID_DATE,
                INSTRUCTOR_USERNAME, FITNESS_CLASS_NAME);

        HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto);
        ResponseEntity<SessionDto> response = client.exchange("/sessions/" + ID1, HttpMethod.PUT, entity,
                SessionDto.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        SessionDto updatedSession = response.getBody();
        assertNotNull(updatedSession);
        assertEquals(ID1, updatedSession.getId());
        assertEquals(PRICE1, updatedSession.getPrice());
        assertEquals(START_TIME2, updatedSession.getStartTime());
        assertEquals(END_TIME2, updatedSession.getEndTime());
        assertEquals(VALID_DATE, updatedSession.getDate());
        assertEquals(INSTRUCTOR_USERNAME, updatedSession.getInstructorUsername());
        assertEquals(FITNESS_CLASS_NAME, updatedSession.getFitnessClassName());
    }

    @Test
    @Order(21)
    public void testDeleteSession() {
        ResponseEntity<Void> response = client.exchange("/sessions/{id}", HttpMethod.DELETE, null, Void.class, ID1);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<SessionDto> recheckResponse = client.exchange("/sessions/" + ID1, HttpMethod.GET, null,
                SessionDto.class);
        assertEquals(HttpStatus.NOT_FOUND, recheckResponse.getStatusCode());
    }
}
