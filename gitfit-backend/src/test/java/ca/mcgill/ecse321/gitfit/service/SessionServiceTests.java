package ca.mcgill.ecse321.gitfit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Time;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Session;
import ca.mcgill.ecse321.gitfit.dao.SessionRepository;

@SpringBootTest
public class SessionServiceTests {
    
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    private static final int SESSION_ID = 1;
    private static final int NONEXISTING_SESSION_ID = 2;
    private static final Time START_TIME = Time.valueOf("10:00:00");
    private static final Time END_TIME = Time.valueOf("11:00:00");
    private static final Date DATE = Date.valueOf("2024-11-11");


    @BeforeEach
    public void setMockOutput() {
        
    }
}
