package ca.mcgill.ecse321.gitfit.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gitfit.dto.DatesDto;
import ca.mcgill.ecse321.gitfit.dto.HoursDto;
import ca.mcgill.ecse321.gitfit.dto.SessionDto;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.service.FitnessClassService;
import ca.mcgill.ecse321.gitfit.service.InstructorAccountService;
import ca.mcgill.ecse321.gitfit.service.SessionService;

@CrossOrigin(origins = "*")
@RestController
public class SessionRestController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private InstructorAccountService instructorService;
    @Autowired
    private FitnessClassService fitnessClassService;

    /**
     * Create a session
     * 
     * @author William Wang (wangwiza)
     * @param sessionDto
     * @return
     */
    @PostMapping(value = { "/sessions", "/sessions/" })
    public SessionDto createSession(@RequestBody SessionDto sessionDto) {
        Instructor instructor = instructorService.getInstructor(sessionDto.getInstructorUsername());
        FitnessClass fitnessClass = fitnessClassService.getFitnessClassByName(sessionDto.getFitnessClassName());
        Session session = sessionService.createSession(instructor, fitnessClass, sessionDto.getPrice(),
                Time.valueOf(sessionDto.getStartTime()),
                Time.valueOf(sessionDto.getEndTime()), Date.valueOf(sessionDto.getDate()));
        return convertToDto(session);
    }

    /**
     * Get all sessions
     * 
     * @author William Wang (wangwiza)
     * @return
     */
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions();
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get a session by ID
     * 
     * @author William Wang (wangwiza)
     * @param id
     * @return
     */
    @GetMapping(value = { "/sessions/{id}", "/sessions/{id}/" })
    public SessionDto getSessionById(@PathVariable("id") int id) {
        Session session = sessionService.getSessionById(id);
        return convertToDto(session);
    }

    /**
     * Get sessions by instructor
     * 
     * @author William Wang (wangwiza)
     * @param instructorUsername
     * @return
     */
    @GetMapping(value = { "/sessions/by-instructor", "/sessions/by-instructor/" })
    public List<SessionDto> getSessionsByInstructor(@RequestBody String instructorUsername) {
        Instructor instructor = instructorService.getInstructor(instructorUsername);
        List<Session> sessions = sessionService.getSessionsByInstructor(instructor);
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get sessions by fitness class
     * 
     * @author William Wang (wangwiza)
     * @param fitnessClassName
     * @return
     */
    @GetMapping(value = { "/sessions/by-class", "/sessions/by-class/" })
    public List<SessionDto> getSessionsByFitnessClass(@RequestBody String fitnessClassName) {
        FitnessClass fitnessClass = fitnessClassService.getFitnessClassByName(fitnessClassName);
        List<Session> sessions = sessionService.getSessionsByFitnessClass(fitnessClass);
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get sessions by instructor and fitness class
     * 
     * @author William Wang (wangwiza)
     * @param instructorUsername
     * @param fitnessClassName
     * @return
     */
    @GetMapping(value = { "/sessions/by-instructor-and-class", "/sessions/by-instructor-and-class/" })
    public List<SessionDto> getSessionsByInstructorAndFitnessClass(@RequestBody SessionDto sessionDto) {
        Instructor instructor = instructorService.getInstructor(sessionDto.getInstructorUsername());
        FitnessClass fitnessClass = fitnessClassService.getFitnessClassByName(sessionDto.getFitnessClassName());
        List<Session> sessions = sessionService.getSessionsByInstructorAndFitnessClass(instructor, fitnessClass);
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get sessions under max price
     * 
     * @author William Wang (wangwiza)
     * @param maxPrice
     * @return
     */
    @GetMapping(value = { "/sessions/by-price", "/sessions/by-price/" })
    public List<SessionDto> getSessionsByMaxPrice(@RequestBody int maxPrice) {
        List<Session> sessions = sessionService.getSessionsByMaxPrice(maxPrice);
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get sessions between two dates
     * 
     * @author William Wang (wangwiza)
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping(value = { "/sessions/by-dates", "/sessions/by-dates/" })
    public List<SessionDto> getSessionsByDate(@RequestBody DatesDto datesDto) {
        List<Session> sessions = sessionService.getSessionsBetweenDates(Date.valueOf(datesDto.getStartDate()),
                Date.valueOf(datesDto.getEndDate()));
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Get sessions between two times
     * 
     * @author William Wang (wangwiza)
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping(value = { "/sessions/by-times", "/sessions/by-times/" })
    public List<SessionDto> getSessionsByTime(@RequestBody HoursDto hoursDto) {
        List<Session> sessions = sessionService.getSessionsBetweenTimes(Time.valueOf(hoursDto.getOpeningTime()),
                Time.valueOf(hoursDto.getClosingTime()));
        List<SessionDto> sessionDtos = new ArrayList<SessionDto>();
        for (Session session : sessions) {
            sessionDtos.add(convertToDto(session));
        }
        return sessionDtos;
    }

    /**
     * Update a session
     * 
     * @author William Wang (wangwiza)
     * @param sessionDto
     * @return
     */
    @PutMapping(value = { "/sessions", "/sessions/" })
    public SessionDto updateSession(@RequestBody SessionDto sessionDto) {
        Session session = sessionService.getSessionById(sessionDto.getId());
        session = sessionService.updateSession(session, sessionDto.getPrice(),
                Time.valueOf(sessionDto.getStartTime()),
                Time.valueOf(sessionDto.getEndTime()), Date.valueOf(sessionDto.getDate()));
        return convertToDto(session);
    }

    /**
     * Delete a session
     * 
     * @author William Wang (wangwiza)
     * @param id
     */
    @DeleteMapping(value = { "/sessions/{id}", "/sessions/{id}/" })
    public void deleteSession(@PathVariable("id") int id) {
        Session session = sessionService.getSessionById(id);
        sessionService.deleteSession(session);
    }

    /**
     * Convert a session to a DTO
     * 
     * @author William Wang (wangwiza)
     * @param session
     * @return
     */
    private SessionDto convertToDto(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("There is no such session!");
        }
        SessionDto sessionDto = new SessionDto(session.getId(), session.getPrice(),
                session.getStartTime().toLocalTime(),
                session.getEndTime().toLocalTime(), session.getDate().toLocalDate(),
                session.getInstructor().getUsername(),
                session.getFitnessClass().getName());
        return sessionDto;
    }
}
