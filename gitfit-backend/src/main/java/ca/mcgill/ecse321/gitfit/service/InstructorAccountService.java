package ca.mcgill.ecse321.gitfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.dto.AccountCreationDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Instructor;

/**
 * This class is responsible for handling instructor account operations
 * 
 * @author Jatin Patel (Jatin-Pat)
 */
@Service
public class InstructorAccountService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SportCenterService sportCenterService;

    @Autowired
    private ValidatorService validatorService;

    /**
     * Retrieve an instructor by username
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @param username
     * @return Instructor object
     * @throws SportCenterException if instructor not found
     */
    @Transactional
    public Instructor getInstructor(String username) {
        Instructor instructor = instructorRepository.findInstructorByUsername(username);
        if (instructor == null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "Instructor not found.");
        }
        return instructor;
    }

    /**
     * Retrieve all instructors
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @return List of all instructors
     * @throws SportCenterException if no instructors found
     */
    @Transactional
    public List<Instructor> getAllInstructors() {
        List<Instructor> list = toList(instructorRepository.findAll());
        if (list.isEmpty()) {
            throw new SportCenterException(HttpStatus.NOT_FOUND, "No current instructors.");
        }

        return list;
    }

    /**
     * Create an instructor. Calls the validator service to validate the input for
     * AccountCreationDto and PasswordCheckDto
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @param username
     * @param email
     * @param password
     * @param lastName
     * @param firstName
     * @return Instructor object
     * @throws SportCenterException if password or fields required for an account
     *                              creation do not meet
     *                              the requirements.
     */
    @Transactional
    public Instructor createInstructor(String username, String email, String password, String lastName,
            String firstName) {

        validatorService.validate(new AccountCreationDto(username, email, lastName, firstName));
        validatorService.validate(new PasswordCheckDto(password));

        Instructor instructor = new Instructor(username, email, password, lastName, firstName,
                sportCenterService.getSportCenter());
        instructorRepository.save(instructor);
        return instructor;
    }

    /**
     * Update an instructor's email. Calls the validator service to validate the
     * input for AccountCreationDto
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @param username
     * @param newPassword
     * @return Instructor object if updated
     * @throws SportCenterException if password does not meet the requirements.
     */
    @Transactional
    public Instructor updateInstructorPassword(String username, String newPassword) {
        Instructor instructor = getInstructor(username);

        validatorService.validate(new PasswordCheckDto(newPassword));

        instructor.setPassword(newPassword);
        instructorRepository.save(instructor);
        return instructor;
    }

    /**
     * Update an instructor's email
     * 
     * @author Jatin Patel (Jatin-Pat)
     * @param iterable
     * @return List containing all the elements from the given iterable
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
