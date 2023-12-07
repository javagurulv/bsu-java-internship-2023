package lv.javaguru.travel.insurance.rest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumRequestLoggerTest {
    private final TravelCalculatePremiumRequestLogger requestLogger = new TravelCalculatePremiumRequestLogger();
    private final Logger testLogger = (Logger)LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    private final DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldLogCorrectWithAllFields() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "personFirstName", "personLastName",
                dateTimeService.createDate("07.12.2055"), dateTimeService.createDate("08.09.2034"));
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        testLogger.addAppender(listAppender);
        requestLogger.log(request);

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(1, logsList.size());
        assertEquals("REQUEST: {\"personFirstName\":\"personFirstName\",\"personLastName\":\"personLastName\",\"agreementDateFrom\":\"2055-12-06\",\"agreementDateTo\":\"2034-09-07\"}",
                logsList.get(0).getMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());
    }

    @Test
    public void shouldLogCorrectWithoutFields() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
                "", "",
                null, null);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        testLogger.addAppender(listAppender);
        requestLogger.log(request);

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(1, logsList.size());
        assertEquals("REQUEST: {\"personFirstName\":\"\",\"personLastName\":\"\",\"agreementDateFrom\":null,\"agreementDateTo\":null}",
                logsList.get(0).getMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());
    }
}