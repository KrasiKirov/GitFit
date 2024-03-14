package main.java.ca.mcgill.ecse321.gitfit.service;

import java.util.Set;

import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidatorService {

    private Validator validator;

    public ValidatorService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(violation.getMessage());
                errorMessage.append("\n");
            }

            throw new SportCenterException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }
    }
}