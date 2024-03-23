package ca.mcgill.ecse321.gitfit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Time;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

import ca.mcgill.ecse321.gitfit.dao.SessionRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;

@SpringBootTest
public class SessionServiceTests {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SportCenterRepository sportCenterRepository;

    @InjectMocks
    private SessionService sessionService;

    private static final int SESSION_ID = 1;
    private static final int SESSION_PRICE = 10;
    private static final Time SESSION_START_TIME = Time.valueOf("10:00:00");
    private static final Time SESSION_END_TIME = Time.valueOf("11:00:00");
    private static final Date SESSION_DATE = Date.valueOf("2024-11-11");

    private static final int SESSION2_PRICE = 20;
    private static final Time SESSION2_START_TIME = Time.valueOf("12:00:00");
    private static final Time SESSION2_END_TIME = Time.valueOf("13:00:00");
    private static final Date SESSION2_DATE = Date.valueOf("2024-11-12");

    private static final int SESSION3_PRICE = 30;
    private static final Time SESSION3_START_TIME = Time.valueOf("14:00:00");
    private static final Time SESSION3_END_TIME = Time.valueOf("15:00:00");
    private static final Date SESSION3_DATE = Date.valueOf("2024-11-13");

    private static final int INVALID_SESSION_ID = 2;
    private static final int INVALID_SESSION_PRICE = -1;
    private static final Time INVALID_SESSION_START_TIME = Time.valueOf("6:00:00");
    private static final Time INVALID_SESSION_END_TIME = Time.valueOf("23:00:00");
    
    private static final Time SPORT_CENTER_OPENING_TIME = Time.valueOf("08:00:00");
    private static final Time SPORT_CENTER_CLOSING_TIME = Time.valueOf("22:00:00");

    private SportCenter SPORT_CENTER = new SportCenter();
    private Session SESSION = new Session();
    private Session SESSION2 = new Session();
    private Session SESSION3 = new Session();

    private Instructor INSTRUCTOR = new Instructor();
    private Instructor INVALID_INSTRUCTOR = new Instructor();

    private FitnessClass FITNESS_CLASS = new FitnessClass();
    private FitnessClass INVALID_FITNESS_CLASS = new FitnessClass();

    @BeforeEach
    public void setMockOutput() {
        SPORT_CENTER.setOpeningTime(SPORT_CENTER_OPENING_TIME);
        SPORT_CENTER.setClosingTime(SPORT_CENTER_CLOSING_TIME);

        SESSION.setId(SESSION_ID);
        SESSION.setPrice(SESSION_PRICE);
        SESSION.setStartTime(SESSION_START_TIME);
        SESSION.setEndTime(SESSION_END_TIME);
        SESSION.setDate(SESSION_DATE);
        SESSION.setInstructor(INSTRUCTOR);
        SESSION.setFitnessClass(FITNESS_CLASS);
        SESSION.setSportCenter(SPORT_CENTER);

        SESSION2.setPrice(SESSION2_PRICE);
        SESSION2.setStartTime(SESSION2_START_TIME);
        SESSION2.setEndTime(SESSION2_END_TIME);
        SESSION2.setDate(SESSION2_DATE);

        SESSION3.setPrice(SESSION3_PRICE);
        SESSION3.setStartTime(SESSION3_START_TIME);
        SESSION3.setEndTime(SESSION3_END_TIME);
        SESSION3.setDate(SESSION3_DATE);

        lenient().when(sessionRepository.save(any(Session.class))).thenReturn(SESSION);
        lenient().when(sessionRepository.findSessionById(SESSION_ID)).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(SESSION_ID)) {
                return SESSION;
            } else {
                return null;
            }
        });
        lenient().when(sessionRepository.findAll()).thenReturn(Arrays.asList(SESSION, SESSION2, SESSION3));
        lenient().when(sessionRepository.findByInstructor(INSTRUCTOR)).thenReturn(Arrays.asList(SESSION));
        lenient().when(sessionRepository.findByFitnessClass(FITNESS_CLASS)).thenReturn(Arrays.asList(SESSION));
        lenient().when(sessionRepository.findByInstructorAndFitnessClass(INSTRUCTOR, FITNESS_CLASS))
                .thenReturn(Arrays.asList(SESSION));
        lenient().when(sessionRepository.findByPriceLessThanEqual(SESSION2_PRICE)).thenReturn(Arrays.asList(SESSION, SESSION2));
        lenient().when(sessionRepository.findByDateBetween(SESSION_DATE, SESSION2_DATE))
                .thenReturn(Arrays.asList(SESSION, SESSION2));
        lenient().when(sessionRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(SESSION2_START_TIME,
                SESSION3_END_TIME)).thenReturn(Arrays.asList(SESSION2, SESSION3));
        lenient().when(sportCenterRepository.findAll()).thenReturn(Arrays.asList(SPORT_CENTER));
    }

    @Test
    public void testCreateSession() {
        lenient().when(sessionRepository.findAll()).thenReturn(Arrays.asList());
        Session session = null;
        try {
            session = sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME,
                    SESSION_END_TIME, SESSION_DATE);
        } catch (SportCenterException e) {
            fail();
        }
        assertNotNull(session);
        verify(sessionRepository, times(1)).save(any(Session.class));
        verify(sportCenterRepository, times(1)).findAll();
    }

    @Test
    public void testCreateSessionNullInstructor() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(null, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME, SESSION_END_TIME,
                    SESSION_DATE);
        }, "All fields must be filled in to create a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionNullFitnessClass() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, null, SESSION_PRICE, SESSION_START_TIME, SESSION_END_TIME,
                    SESSION_DATE);
        }, "All fields must be filled in to create a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionNullStartTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, null, SESSION_END_TIME, SESSION_DATE);
        }, "All fields must be filled in to create a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionNullEndTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME, null, SESSION_DATE);
        }, "All fields must be filled in to create a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionNullDate() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME, SESSION_END_TIME,
                    null);
        }, "All fields must be filled in to create a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionNegativePrice() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, INVALID_SESSION_PRICE, SESSION_START_TIME, SESSION_END_TIME,
                    SESSION_DATE);
        }, "Price must be free or positive");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionEndTimeBeforeStartTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_END_TIME, SESSION_START_TIME,
                    SESSION_DATE);
        }, "End time must be after start time");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionEndTimeAfterClosingTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME, INVALID_SESSION_END_TIME,
                    SESSION_DATE);
        }, "Time must be within sport center hours");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionStartTimeBeforeOpeningTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, INVALID_SESSION_START_TIME, SESSION_END_TIME,
                    SESSION_DATE);
        }, "Time must be within sport center hours");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testCreateSessionTimeSlotTaken() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.createSession(INSTRUCTOR, FITNESS_CLASS, SESSION_PRICE, SESSION_START_TIME, SESSION_END_TIME,
                    SESSION_DATE);
        }, "Time slot is already taken");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void testFindAllSessions() {
        List<Session> sessions = sessionService.findAllSessions();

        assertEquals(3, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        assertEquals(SESSION2, sessions.get(1));
        assertEquals(SESSION3, sessions.get(2));
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    public void testFindSessionById() {
        Session session = sessionService.findSessionById(SESSION_ID);

        assertNotNull(session);
        assertEquals(SESSION, session);
        verify(sessionRepository, times(1)).findSessionById(SESSION_ID);
    }

    @Test
    public void testFindSessionByIdInvalidId() {
        Session session = sessionService.findSessionById(INVALID_SESSION_ID);

        assertEquals(null, session);
        verify(sessionRepository, times(1)).findSessionById(INVALID_SESSION_ID);
    }

    @Test
    public void testFindSessionsByInstructor() {
        List<Session> sessions = sessionService.findSessionsByInstructor(INSTRUCTOR);

        assertEquals(1, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        verify(sessionRepository, times(1)).findByInstructor(INSTRUCTOR);
    }

    @Test
    public void testFindSessionsByInstructorNullInstructor() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsByInstructor(null);
        }, "Instructor must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByInstructor(null);
    }

    @Test
    public void testFindSessionsByInstructorInvalidInstructor() {
        List<Session> sessions = sessionService.findSessionsByInstructor(INVALID_INSTRUCTOR);

        assertEquals(0, sessions.size());
        verify(sessionRepository, times(1)).findByInstructor(INVALID_INSTRUCTOR);
    }

    @Test
    public void testFindSessionsByFitnessClass() {
        List<Session> sessions = sessionService.findSessionsByFitnessClass(FITNESS_CLASS);

        assertEquals(1, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        verify(sessionRepository, times(1)).findByFitnessClass(FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByFitnessClassNullFitnessClass() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsByFitnessClass(null);
        }, "Fitness class must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByFitnessClass(null);
    }

    @Test
    public void testFindSessionsByFitnessClassInvalidFitnessClass() {
        List<Session> sessions = sessionService.findSessionsByFitnessClass(INVALID_FITNESS_CLASS);

        assertEquals(0, sessions.size());
        verify(sessionRepository, times(1)).findByFitnessClass(INVALID_FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByInstructorAndFitnessClass() {
        List<Session> sessions = sessionService.findSessionsByInstructorAndFitnessClass(INSTRUCTOR, FITNESS_CLASS);

        assertEquals(1, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        verify(sessionRepository, times(1)).findByInstructorAndFitnessClass(INSTRUCTOR, FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByInstructorAndFitnessClassNullInstructor() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsByInstructorAndFitnessClass(null, FITNESS_CLASS);
        }, "Instructor and fitness class must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByInstructorAndFitnessClass(null, FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByInstructorAndFitnessClassNullFitnessClass() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsByInstructorAndFitnessClass(INSTRUCTOR, null);
        }, "Instructor and fitness class must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByInstructorAndFitnessClass(INSTRUCTOR, null);
    }

    @Test
    public void testFindSessionsByInstructorAndFitnessClassInvalidInstructor() {
        List<Session> sessions = sessionService.findSessionsByInstructorAndFitnessClass(INVALID_INSTRUCTOR, FITNESS_CLASS);

        assertEquals(0, sessions.size());
        verify(sessionRepository, times(1)).findByInstructorAndFitnessClass(INVALID_INSTRUCTOR, FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByInstructorAndFitnessClassInvalidFitnessClass() {
        List<Session> sessions = sessionService.findSessionsByInstructorAndFitnessClass(INSTRUCTOR, INVALID_FITNESS_CLASS);

        assertEquals(0, sessions.size());
        verify(sessionRepository, times(1)).findByInstructorAndFitnessClass(INSTRUCTOR, INVALID_FITNESS_CLASS);
    }

    @Test
    public void testFindSessionsByMaxPrice() {
        List<Session> sessions = sessionService.findSessionsByMaxPrice(SESSION2_PRICE);

        assertEquals(2, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        assertEquals(SESSION2, sessions.get(1));
        verify(sessionRepository, times(1)).findByPriceLessThanEqual(SESSION2_PRICE);
    }

    @Test
    public void testFindSessionsByMaxPriceNegativePrice() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsByMaxPrice(-1);
        }, "Price must be free or positive");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByPriceLessThanEqual(-1);
    }

    @Test
    public void testFindSessionsBetweenDates() {
        List<Session> sessions = sessionService.findSessionsBetweenDates(SESSION_DATE, SESSION2_DATE);

        assertEquals(2, sessions.size());
        assertEquals(SESSION, sessions.get(0));
        assertEquals(SESSION2, sessions.get(1));
        verify(sessionRepository, times(1)).findByDateBetween(SESSION_DATE, SESSION2_DATE);
    }

    @Test
    public void testFindSessionsBetweenDatesNullStartDate() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenDates(null, SESSION2_DATE);
        }, "Start and end date must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByDateBetween(null, SESSION2_DATE);
    }

    @Test
    public void testFindSessionsBetweenDatesNullEndDate() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenDates(SESSION_DATE, null);
        }, "Start and end date must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByDateBetween(SESSION_DATE, null);
    }

    @Test
    public void testFindSessionsBetweenDatesStartDateAfterEndDate() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenDates(SESSION2_DATE, SESSION_DATE);
        }, "Start date must be before end date");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByDateBetween(SESSION2_DATE, SESSION_DATE);
    }

    @Test
    public void testFindSessionsBetweenTimes() {
        List<Session> sessions = sessionService.findSessionsBetweenTimes(SESSION2_START_TIME, SESSION3_END_TIME);

        assertEquals(2, sessions.size());
        assertEquals(SESSION2, sessions.get(0));
        assertEquals(SESSION3, sessions.get(1));
        verify(sessionRepository, times(1)).findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(SESSION2_START_TIME,
                SESSION3_END_TIME);
    }

    @Test
    public void testFindSessionsBetweenTimesNullMinTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenTimes(null, SESSION3_END_TIME);
        }, "Start and end time must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(null, SESSION3_END_TIME);
    }

    @Test
    public void testFindSessionsBetweenTimesNullMaxTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenTimes(SESSION2_START_TIME, null);
        }, "Start and end time must be filled in");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(SESSION2_START_TIME, null);
    }

    @Test
    public void testFindSessionsBetweenTimesMinTimeAfterMaxTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.findSessionsBetweenTimes(SESSION3_END_TIME, SESSION2_START_TIME);
        }, "Start time must be before end time");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(SESSION3_END_TIME,
                SESSION2_START_TIME);
    }

    @Test
    public void testUpdateSession() {
        lenient().when(sessionRepository.findAll()).thenReturn(Arrays.asList(SESSION));
        Session session = null;
        try {
            session = sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_START_TIME, SESSION2_END_TIME, SESSION2_DATE);
        } catch (SportCenterException e) {
            fail();
        }
        assertNotNull(session);
        assertEquals(SESSION_ID, session.getId());
        assertEquals(SESSION2_PRICE, session.getPrice());
        assertEquals(SESSION2_START_TIME, session.getStartTime());
        assertEquals(SESSION2_END_TIME, session.getEndTime());
        assertEquals(SESSION2_DATE, session.getDate());
        verify(sessionRepository, times(1)).save(any(Session.class));
    }

    @Test
    public void testUpdateSessionNullSession() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(null, SESSION2_PRICE, SESSION2_START_TIME, SESSION2_END_TIME, SESSION2_DATE);
        }, "All fields must be filled in to update a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(null);
    }

    @Test
    public void testUpdateSessionNullStartTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, null, SESSION2_END_TIME, SESSION2_DATE);
        }, "All fields must be filled in to update a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionNullEndTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_START_TIME, null, SESSION2_DATE);
        }, "All fields must be filled in to update a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionNullDate() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_START_TIME, SESSION2_END_TIME, null);
        }, "All fields must be filled in to update a session");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionNegativePrice() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, INVALID_SESSION_PRICE, SESSION2_START_TIME, SESSION2_END_TIME, SESSION2_DATE);
        }, "Price must be free or positive");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionEndTimeBeforeStartTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_END_TIME, SESSION2_START_TIME, SESSION2_DATE);
        }, "End time must be after start time");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionEndTimeAfterClosingTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_START_TIME, INVALID_SESSION_END_TIME, SESSION2_DATE);
        }, "Time must be within sport center hours");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionStartTimeBeforeOpeningTime() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, INVALID_SESSION_START_TIME, SESSION2_END_TIME, SESSION2_DATE);
        }, "Time must be within sport center hours");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testUpdateSessionTimeSlotTaken() {
        lenient().when(sessionRepository.findAll()).thenReturn(Arrays.asList(SESSION, SESSION2));
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.updateSession(SESSION, SESSION2_PRICE, SESSION2_START_TIME, SESSION2_END_TIME, SESSION2_DATE);
        }, "Time slot is already taken");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).save(SESSION);
    }

    @Test
    public void testDeleteSession() {
        try {
            sessionService.deleteSession(SESSION);
        } catch (SportCenterException e) {
            fail();
        }
        verify(sessionRepository, times(1)).delete(SESSION);
    }

    @Test
    public void testDeleteSessionNullSession() {
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            sessionService.deleteSession(null);
        }, "Session must be filled in to delete");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        verify(sessionRepository, never()).delete(null);
    }
}
