package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseTest {

    @Mock
    private TravelCalculatePremiumDatabaseLogger logger;

    @InjectMocks
    private Database database;

    @BeforeEach
    public void initMock() {
        doNothing().when(logger).log(any());
    }

    @Test
    public void testConnectionCreation() {
    }

    @Test
    public void queryTest() throws SQLException {
        database.executeQuery("select count(*) from medical_risk_factors");
    }
}
