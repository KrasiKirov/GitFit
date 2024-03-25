package ca.mcgill.ecse321.gitfit.service;

import ca.mcgill.ecse321.gitfit.dao.FitnessClassRepository;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.FitnessClass;
import ca.mcgill.ecse321.gitfit.model.FitnessClassApprovalStatus;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class FitnessClassServiceTests {
    @Mock
    private FitnessClassRepository fitnessClassRepository;
    @Mock
    private SportCenterService sportCenterService;
    @InjectMocks
    private FitnessClassService fitnessClassService;

    @Test
    public void createValidFitnessClassTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);


        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        FitnessClass createdFitnessClass = fitnessClassService.createFitnessClass(name, description);


        assertNotNull(createdFitnessClass);
        assertEquals(name, createdFitnessClass.getName());
        assertEquals(description, createdFitnessClass.getDescription());
        verify(fitnessClassRepository, times(1)).save(any(FitnessClass.class));

    }

    @Test
    public void createFitnessClassInvalidNameOrDescriptionTest() {
        SportCenter sportCenter = new SportCenter();

        String name = null;
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);

        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.createFitnessClass(name, description);
        });

        assertEquals("Must provide a name and a description.", exception.getMessage());
    }

    @Test
    public void createFitnessClassAlreadyExistsTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);
        when(sportCenterService.getSportCenter()).thenReturn(sportCenter);
        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);


        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.createFitnessClass(name, description);
        });

        assertEquals("There is already a fitness class called " + name + ".", exception.getMessage());
    }

    @Test
    public void findFitnessClassByNameTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);

        FitnessClass foundFitnessClass = fitnessClassService.findFitnessClassByName(name);

        assertNotNull(foundFitnessClass);
        assertEquals(name, foundFitnessClass.getName());
        assertEquals(description, foundFitnessClass.getDescription());
    }

    @Test
    public void findFitnessClassByNameNotFoundTest() {
        String name = "TestFitnessClass";
        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(null);

        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.findFitnessClassByName(name);
        });

        assertEquals("Fitness class not found.", exception.getMessage());
    }

    @Test
    public void updateApprovalStatusTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);

        String status = "APPROVED";
        FitnessClass updatedFitnessClass = fitnessClassService.updateApprovalStatus(name, status);

        assertNotNull(updatedFitnessClass);
        assertEquals(name, updatedFitnessClass.getName());
        assertEquals(description, updatedFitnessClass.getDescription());
        assertEquals(FitnessClassApprovalStatus.valueOf(status), updatedFitnessClass.getApprovalStatus());
    }

    @Test
    public void updateApprovalStatusInvalidStatusTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);

        String status = "INVALID";
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.updateApprovalStatus(name, status);
        });

        assertEquals("Invalid status.", exception.getMessage());
    }

    @Test
    public void updateApprovalStatusInvalidNameOrStatusTest() {
        SportCenter sportCenter = new SportCenter();

        String name = null;
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);

        String status = "APPROVED";
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.updateApprovalStatus(name, status);
        });

        assertEquals("Must provide a name and a status.", exception.getMessage());
    }

    @Test
    public void updateFitnessClassTest() {
        SportCenter sportCenter = new SportCenter();

        String name = "TestFitnessClass";
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);

        String newDescription = "NewDescription";
        FitnessClass updatedFitnessClass = fitnessClassService.updateFitnessClass(name, newDescription);

        assertNotNull(updatedFitnessClass);
        assertEquals(name, updatedFitnessClass.getName());
        assertEquals(newDescription, updatedFitnessClass.getDescription());
    }

    @Test
    public void updateFitnessClassInvalidNameOrDescriptionTest() {
        SportCenter sportCenter = new SportCenter();

        String name = null;
        String description = "TestDescription";

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName(name);
        fitnessClass.setDescription(description);
        fitnessClass.setSportCenter(sportCenter);

        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(fitnessClass);
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);

        String newDescription = "NewDescription";
        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.updateFitnessClass(name, newDescription);
        });

        assertEquals("Must provide a name and a description.", exception.getMessage());
    }

    @Test
    public void updateFitnessClassNotFoundTest() {
        String name = "TestFitnessClass";
        when(fitnessClassRepository.findFitnessClassByName(name)).thenReturn(null);

        SportCenterException exception = assertThrows(SportCenterException.class, () -> {
            fitnessClassService.updateFitnessClass(name, "NewDescription");
        });

        assertEquals("Fitness class not found.", exception.getMessage());
    }

    @Test
    public void deleteRejectedFitnessClassesTest() {
        fitnessClassService.deleteRejectedFitnessClasses();
        verify(fitnessClassRepository, times(1)).deleteRejectedFitnessClasses();
    }

}
