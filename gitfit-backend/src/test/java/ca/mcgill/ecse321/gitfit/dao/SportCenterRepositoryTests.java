package ca.mcgill.ecse321.gitfit.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.model.SportCenter;

@SpringBootTest
public class SportCenterRepositoryTests {
    @Autowired
    private SportCenterRepository sportCenterRepository;

    @AfterEach
    public void clearDatabase() {
        sportCenterRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadSportCenter() {
        // Create sport center
        int maxCapacity = 100;
        Time openingTime = Time.valueOf("08:00:00");
        Time closingTime = Time.valueOf("20:00:00");

        SportCenter sportCenter = new SportCenter();
        sportCenter.setMaxCapacity(maxCapacity);
        sportCenter.setOpeningTime(openingTime);
        sportCenter.setClosingTime(closingTime);

        // Save sport center
        sportCenter = sportCenterRepository.save(sportCenter);

        // Read sport center from database
        sportCenter = sportCenterRepository.findSportCenterById(sportCenter.getId());

        // Assert sport center is not null and has correct attributes
        assertNotNull(sportCenter);
        assertEquals(maxCapacity, sportCenter.getMaxCapacity());
        assertEquals(openingTime, sportCenter.getOpeningTime());
        assertEquals(closingTime, sportCenter.getClosingTime());
    }

}
