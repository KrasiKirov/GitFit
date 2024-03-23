package ca.mcgill.ecse321.gitfit.controller;

import ca.mcgill.ecse321.gitfit.service.SessionService;
import ca.mcgill.ecse321.gitfit.service.InstructorService;
import ca.mcgill.ecse321.gitfit.service.FitnessClassService;
import ca.mcgill.ecse321.gitfit.service.SportCenterService;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.dto.SessionDto;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
public class SessionRestController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private InstructorService instructorService;
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
    public SessionDto makeSession(@RequestBody SessionDto sessionDto) {
        Instructor instructor = instructorService.getInstructor(sessionDto.getInstructorUsername());
        FitnessClass fitnessClass = fitnessClassService.getFitnessClass(sessionDto.getFitnessClassName());
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
        List<Session> sessions = sessionService.findAllSessions();
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
        Session session = sessionService.findSessionById(id);
        return convertToDto(session);
    }

    /**
     * Get sessions by instructor
     * 
     * @author William Wang (wangwiza)
     * @param instructorUsername
     * @return
     */
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByInstructor(@RequestParam("instructorUsername") String instructorUsername) {
        Instructor instructor = instructorService.getInstructor(instructorUsername);
        List<Session> sessions = sessionService.findSessionsByInstructor(instructor);
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
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByFitnessClass(@RequestParam("fitnessClassName") String fitnessClassName) {
        FitnessClass fitnessClass = fitnessClassService.getFitnessClass(fitnessClassName);
        List<Session> sessions = sessionService.findSessionsByFitnessClass(fitnessClass);
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
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByInstructorAndFitnessClass(
            @RequestParam("instructorUsername") String instructorUsername,
            @RequestParam("fitnessClassName") String fitnessClassName) {
        Instructor instructor = instructorService.getInstructor(instructorUsername);
        FitnessClass fitnessClass = fitnessClassService.getFitnessClass(fitnessClassName);
        List<Session> sessions = sessionService.findSessionsByInstructorAndFitnessClass(instructor, fitnessClass);
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
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByMaxPrice(@RequestParam("maxPrice") int maxPrice) {
        List<Session> sessions = sessionService.findSessionsByMaxPrice(maxPrice);
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
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByDate(@RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate) {
        List<Session> sessions = sessionService.findSessionsByDate(startDate, endDate);
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
    @GetMapping(value = { "/sessions", "/sessions/" })
    public List<SessionDto> getSessionsByTime(@RequestParam("startTime") Time startTime,
            @RequestParam("endTime") Time endTime) {
        List<Session> sessions = sessionService.findSessionsByTime(startTime, endTime);
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
        Session session = sessionService.findSessionById(sessionDto.getId());
        Instructor instructor = instructorService.getInstructor(sessionDto.getInstructorUsername());
        FitnessClass fitnessClass = fitnessClassService.getFitnessClass(sessionDto.getFitnessClassName());
        session = sessionService.updateSession(session, instructor, fitnessClass, sessionDto.getPrice(),
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
        Session session = sessionService.findSessionById(id);
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
