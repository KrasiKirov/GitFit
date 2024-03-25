package ca.mcgill.ecse321.gitfit.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.gitfit.dao.FitnessClassRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.hibernate.mapping.Constraint;

@ExtendWith(MockitoExtension.class)
public class TestFitnessClassService {
    @Mock
    private FitnessClassRepository fitnessClassRepository;

    @Mock
    private SportCenterService sportCenterService;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private FitnessClassService fitnessClassService;

    private static final String FITNESSCLASS_KEY = "TestFitnessClass";
    private static final String NONEXISTING_KEY = "NotAFitnessClass";
    
    @BeforeEach
    public void setMockOutput() {
        lenient().when(fitnessClassRepository.findFitnessClassByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(FITNESSCLASS_KEY)) {
                FitnessClass fitnessClass = new FitnessClass();
                fitnessClass.setName(FITNESSCLASS_KEY);
                return fitnessClass;
            } else {
                return null;
            }
        });
    
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(fitnessClassRepository.save(any(FitnessClass.class))).thenAnswer(returnParameterAsAnswer);

        SportCenter sportCenter = new SportCenter();
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        doAnswer(invocation -> {
            Object argument = invocation.getArgument(0);
            Set<ConstraintViolation<Object>> violations = validator.validate(argument);
            if (!violations.isEmpty()) {
                ConstraintViolation<?> violation = violations.iterator().next();
                throw new SportCenterException(HttpStatus.BAD_REQUEST, violation.getMessage());
            }
            return null;
        }).when(validatorService).validate(any());
    }

    @Test
    public void testGetFitnessClassByName() {
        assertEquals(FITNESSCLASS_KEY, fitnessClassService.findFitnessClassByName(FITNESSCLASS_KEY).getName());
    }

    @Test
    public void testGetFitnessClassByNameNotFound() {
        Exception exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.findFitnessClassByName(NONEXISTING_KEY);
        });

        String expectedMessage = "Fitness class not found.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetAllFitnessClasses() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(FITNESSCLASS_KEY);
        when(fitnessClassRepository.findAll()).thenReturn(java.util.List.of(fitnessClass));
        assertEquals(1, fitnessClassService.findAllFitnessClasses().size());
    }

    @Test
    public void testGetApprovedFitnessClasses() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(FITNESSCLASS_KEY);
        when(fitnessClassRepository.findAll()).thenReturn(java.util.List.of(fitnessClass));
        assertEquals(1, fitnessClassService.findApprovedClasses().size());
    }

    @Test
    public void testGetPendingFitnessClasses() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(FITNESSCLASS_KEY);
        when(fitnessClassRepository.findAll()).thenReturn(java.util.List.of(fitnessClass));
        assertEquals(1, fitnessClassService.findPendingClasses().size());
    }



}
