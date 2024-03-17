package ca.mcgill.ecse321.gitfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import ca.mcgill.ecse321.gitfit.dao.InstructorRepository;
import ca.mcgill.ecse321.gitfit.dto.AccountCreationDto;
import ca.mcgill.ecse321.gitfit.dto.PasswordCheckDto;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Instructor;
import ca.mcgill.ecse321.gitfit.model.Owner;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import ca.mcgill.ecse321.gitfit.service.SportCenterService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class InstructorAccountService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SportCenterService sportCenterService;

    @Autowired
    private ValidatorService validatorService;

    @Transactional
    public Instructor getInstructor(String username) {
        return instructorRepository.findInstructorByUsername(username);
    }

    @Transactional
    public List<Instructor> getAllInstructors() {
        List<Instructor> list = toList(instructorRepository.findAll());
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Transactional
    public Instructor createInstructor(String username, String email, String password, String lastName,
            String firstName) {

        validatorService.validate(new AccountCreationDto());
        validatorService.validate(new PasswordCheckDto(password));

        Instructor instructor = new Instructor(username, email, password, lastName, firstName,
                sportCenterService.getSportCenter());
        instructorRepository.save(instructor);
        return instructor;
    }

    @Transactional
    public Instructor updateInstructorPassword(String username, String newPassword) {
        Instructor instructor = instructorRepository.findInstructorByUsername(username);

        validatorService.validate(new PasswordCheckDto(newPassword));

        instructor.setPassword(newPassword);
        instructorRepository.save(instructor);
        return instructor;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
