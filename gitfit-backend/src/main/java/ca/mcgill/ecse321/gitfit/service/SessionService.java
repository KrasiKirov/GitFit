package ca.mcgill.ecse321.gitfit.service;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.gitfit.dao.SessionRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SportCenterRepository sportCenterRepository;

    /**
     * Create a session
     * 
     * @author William Wang (wangwiza)
     * @param instructor
     * @param fitnessClass
     * @param price
     * @param endTime
     * @param startTime
     * @param date
     * @return
     */
    @Transactional
    public Session createSession(Instructor instructor, FitnessClass fitnessClass, int price, Time startTime,
            Time endTime, Date date) {
        if (instructor == null || fitnessClass == null || endTime == null || startTime == null || date == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "All fields must be filled in to create a session");
        }
        if (price < 0) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Price must be free or positive");
        }
        if (endTime.before(startTime)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "End time must be after start time");
        }
        if (date.before(new Date(System.currentTimeMillis()))) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Date must be in the future");
        }
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        if (endTime.after(sportCenter.getClosingTime()) || startTime.before(sportCenter.getOpeningTime())) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Time must be within sport center hours");
        }
        List<Session> sessions = findAllSessions();
        for (Session session : sessions) {
            if (isSlotTakenByExistingSession(session, startTime, endTime, date)) {
                throw new SportCenterException(HttpStatus.BAD_REQUEST, "Time slot is already taken");
            }
        }

        Session session = new Session();
        session.setInstructor(instructor);
        session.setFitnessClass(fitnessClass);
        session.setPrice(price);
        session.setEndTime(endTime);
        session.setStartTime(startTime);
        session.setDate(date);
        session.setSportCenter(sportCenter);
        session = sessionRepository.save(session);
        return session;
    }

    /**
     * Get all sessions
     * 
     * @author William Wang (wangwiza)
     * @return
     */
    @Transactional
    public List<Session> findAllSessions() {
        return toList(sessionRepository.findAll());
    };

    /**
     * Get session by id
     * 
     * @author William Wang (wangwiza)
     * @param id
     * @return
     */
    @Transactional
    public Session findSessionById(int id) {
        return sessionRepository.findSessionById(id);
    };

    /**
     * Get sessions by instructor
     * 
     * @author William Wang (wangwiza)
     * @param instructor
     * @return
     */
    @Transactional
    public List<Session> findSessionsByInstructor(Instructor instructor) {
        if (instructor == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Instructor must be filled in");
        }
        return toList(sessionRepository.findByInstructor(instructor));
    };

    /**
     * Get sessions by fitness class
     * 
     * @author William Wang (wangwiza)
     * @param fitnessClass
     * @return
     */
    @Transactional
    public List<Session> findSessionsByFitnessClass(FitnessClass fitnessClass) {
        if (fitnessClass == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Fitness class must be filled in");
        }
        return toList(sessionRepository.findByFitnessClass(fitnessClass));
    };

    /**
     * Get sessions by instructor and fitness class
     * 
     * @author William Wang (wangwiza)
     * @param instructor
     * @param fitnessClass
     * @return
     */
    @Transactional
    public List<Session> findSessionsByInstructorAndFitnessClass(Instructor instructor, FitnessClass fitnessClass) {
        if (instructor == null || fitnessClass == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Instructor and fitness class must be filled in");
        }
        return toList(sessionRepository.findByInstructorAndFitnessClass(instructor, fitnessClass));
    };

    /**
     * Get sessions by max price
     * 
     * @author William Wang (wangwiza)
     * @param price
     * @return
     */
    @Transactional
    public List<Session> findSessionsByMaxPrice(int price) {
        if (price < 0) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Price must be free or positive");
        }
        return toList(sessionRepository.findByPriceLessThanEqual(price));
    };

    /**
     * Get sessions between dates
     * 
     * @author William Wang (wangwiza)
     * @param startDate
     * @param endDate
     * @return
     */
    @Transactional
    public List<Session> findSessionsBetweenDates(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Start and end date must be filled in");
        }
        if (startDate.after(endDate)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Start date must be before end date");
        }
        return toList(sessionRepository.findByDateBetween(startDate, endDate));
    };

    /**
     * Get sessions between times
     * 
     * @author William Wang (wangwiza)
     * @param minTime
     * @param maxTime
     * @return
     */
    @Transactional
    public List<Session> findSessionsBetweenTimes(Time minTime, Time maxTime) {
        if (minTime == null || maxTime == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Start and end time must be filled in");
        }
        if (minTime.after(maxTime)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Start time must be before end time");
        }
        return toList(sessionRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(minTime, maxTime));
    };

    /**
     * Update session
     * 
     * @author William Wang (wangwiza)
     * @param newPrice
     * @param newStartTime
     * @param newEndTime
     * @param newDate
     * @return
     */
    @Transactional
    public Session updateSession(Session session, int newPrice, Time newStartTime, Time newEndTime, Date newDate) {
        if (session == null ||newStartTime == null || newEndTime == null || newDate == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "All fields must be filled in to update a session");
        }
        if (newPrice < 0) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Price must be free or positive");
        }
        if (newEndTime.before(newStartTime)) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "End time must be after start time");
        }
        SportCenter sportCenter = sportCenterRepository.findAll().iterator().next();
        if (sportCenter == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "No sport center found");
        }
        if (newEndTime.after(sportCenter.getClosingTime()) || newStartTime.before(sportCenter.getOpeningTime())) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Time must be within sport center hours");
        }
        List<Session> sessions = findAllSessions();
        for (Session other : sessions) {
            if (isSlotTakenByExistingSession(other, newStartTime, newEndTime, newDate) && !other.equals(session)) {
                System.out.println(other);
                throw new SportCenterException(HttpStatus.BAD_REQUEST, "Time slot is already taken");
            }
        }
        session.setPrice(newPrice);
        session.setStartTime(newStartTime);
        session.setEndTime(newEndTime);
        session.setDate(newDate);
        session = sessionRepository.save(session);
        return session;
    }

    /**
     * Delete session
     * 
     * @author William Wang (wangwiza)
     * @param session
     * @return
     */
    @Transactional
    public void deleteSession(Session session) {
        if (session == null) {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "Session must be filled in to delete");
        }
        sessionRepository.delete(session);
    }

    /**
     * toList helper method (@author eventRegistration authors)
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    private Boolean isSlotTakenByExistingSession(Session existingSession, Time startTime, Time endTime, Date date) {
        if (existingSession.getDate().equals(date)) {
            Time existingStartTime = existingSession.getStartTime();
            Time existingEndTime = existingSession.getEndTime();
            if (existingStartTime.equals(startTime) || existingEndTime.equals(endTime) ||
                (existingStartTime.before(startTime) && existingEndTime.after(startTime)) ||
                (existingStartTime.before(endTime) && existingEndTime.after(endTime)) ||
                (existingStartTime.after(startTime) && existingEndTime.before(endTime))) {
                return true;
            }
        }
        return false;
    }
}
