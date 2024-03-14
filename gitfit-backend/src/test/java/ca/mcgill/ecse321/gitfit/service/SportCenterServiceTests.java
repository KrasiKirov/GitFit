package ca.mcgill.ecse321.gitfit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.SportCenter;
import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;

@SpringBootTest
public class SportCenterServiceTests {

    @Mock
    private SportCenterRepository sportCenterRepository;

    @InjectMocks
    private SportCenterService sportCenterService;

    private static final int CENTER_ID = 1360;
    private static final String CENTER_NAME = "McGill Gym";
    private static final int CENTER_MAX_CAPACITY = 100;
    private static final Time CENTER_OPEN_TIME = Time.valueOf("08:00:00");
    private static final Time CENTER_CLOSE_TIME = Time.valueOf("22:00:00");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(sportCenterRepository.findAll()).thenReturn(Arrays.asList(
                createSportCenter(CENTER_ID, CENTER_NAME, CENTER_MAX_CAPACITY, CENTER_OPEN_TIME, CENTER_CLOSE_TIME)));
        lenient().when(sportCenterRepository.save(any(SportCenter.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });
    }

    @Test
    public void testGetSportCenter() {
        // Act
        SportCenter readSportCenter = sportCenterService.getSportCenter();

        // Assert
        assertNotNull(readSportCenter);
        assertEquals(CENTER_ID, readSportCenter.getId());
        assertEquals(CENTER_NAME, readSportCenter.getName());
        assertEquals(CENTER_MAX_CAPACITY, readSportCenter.getMaxCapacity());
        assertEquals(CENTER_OPEN_TIME, readSportCenter.getOpeningTime());
        assertEquals(CENTER_CLOSE_TIME, readSportCenter.getClosingTime());
    }

    @Test
    public void testSetSportCenterName() {
        try {
            sportCenterService.setSportCenterName("UWU-Town");
        } catch (SportCenterException e) {
            fail();
        }
    }

    @Test
    public void testSetSportCenterNameNull() {
        String error = "";
        try {
            sportCenterService.setSportCenterName(null);
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Name cannot be null or empty", error);
    }

    @Test
    public void testSetSportCenterNameEmpty() {
        String error = "";
        try {
            sportCenterService.setSportCenterName("");
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Name cannot be null or empty", error);
    }

    @Test
    public void testSetSportCenterNameSpaces() {
        String error = "";
        try {
            sportCenterService.setSportCenterName("   ");
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Name cannot be null or empty", error);
    }

    @Test
    public void testSetSportCenterMaxCapacity() {
        try {
            sportCenterService.setSportCenterMaxCapacity(200);
        } catch (SportCenterException e) {
            fail();
        }
    }

    @Test
    public void testSetSportCenterMaxCapacityNegative() {
        String error = "";
        try {
            sportCenterService.setSportCenterMaxCapacity(-1);
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Max capacity cannot be negative", error);
    }

    @Test
    public void testSetOpenHours() {
        try {
            sportCenterService.setOpenHours(Time.valueOf("07:00:00"), Time.valueOf("23:00:00"));
        } catch (SportCenterException e) {
            fail();
        }
    }

    @Test
    public void testSetOpenHoursNull() {
        String error = "";
        try {
            sportCenterService.setOpenHours(null, null);
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Opening and closing time cannot be null", error);
    }

    @Test
    public void testSetOpenHoursClosingBeforeOpening() {
        String error = "";
        try {
            sportCenterService.setOpenHours(Time.valueOf("23:00:00"), Time.valueOf("07:00:00"));
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Opening time cannot be after closing time", error);
    }

    @Test
    public void testSetOpenHoursClosingEqualsOpening() {
        String error = "";
        try {
            sportCenterService.setOpenHours(Time.valueOf("07:00:00"), Time.valueOf("07:00:00"));
        } catch (SportCenterException e) {
            error = e.getMessage();
        }
        assertEquals("Opening time cannot be same as closing time", error);
    }

    private static SportCenter createSportCenter(int id, String name, int maxCapacity, Time openingTime,
            Time closingTime) {
        SportCenter sportCenter = new SportCenter();
        sportCenter.setId(id);
        sportCenter.setName(name);
        sportCenter.setOpeningTime(openingTime);
        sportCenter.setClosingTime(closingTime);
        return sportCenter;
    }
}