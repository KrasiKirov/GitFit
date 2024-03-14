package ca.mcgill.ecse321.gitfit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;

@SpringBootTest
public class SportCenterServiceTests {
    
    @Mock
    private SportCenterRepository repo;

    @InjectMocks
    private SportCenterService service;

    @Test
    public void testGetSportCenterExisting() {
        // Set up
        String name = "McGill Gym";
        int maxCapacity = 100;
        Time openingTime = Time.valueOf("08:00:00");
        Time closingTime = Time.valueOf("22:00:00");
        SportCenter mcgillGym = new SportCenter();
        mcgillGym.setName(name);
        mcgillGym.setMaxCapacity(maxCapacity);
        mcgillGym.setOpeningTime(openingTime);
        mcgillGym.setClosingTime(closingTime);
        when(repo.findAll().iterator().next()).thenReturn(mcgillGym);


        // Act
        SportCenter readSportCenter = service.getSportCenter();

        // Assert
        assertNotNull(readSportCenter);
        assertEquals(mcgillGym.getName(), readSportCenter.getName());
        assertEquals(mcgillGym.getMaxCapacity(), readSportCenter.getMaxCapacity());
        assertEquals(mcgillGym.getOpeningTime(), readSportCenter.getOpeningTime());
        assertEquals(mcgillGym.getClosingTime(), readSportCenter.getClosingTime());
    }
}