package lv.javaguru.travel.insurance.rest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumResponseLoggerTest {
    private final TravelCalculatePremiumResponseLogger responseLogger = new TravelCalculatePremiumResponseLogger();
    private final Logger testLogger = (Logger)LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    private final DateTimeService dateTimeService = new DateTimeService();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldLogCorrectWithAllFields() throws JsonProcessingException {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(
                "personFirstName", "personLastName",
                dateTimeService.createDate("07.12.2055"), dateTimeService.createDate("08.09.2034"),
                BigDecimal.valueOf(45));
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        testLogger.addAppender(listAppender);
        responseLogger.log(response);

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(1, logsList.size());
        assertEquals("RESPONSE: " + objectMapper.writeValueAsString(response),
                logsList.get(0).getMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());
    }

    @Test
    public void shouldLogCorrectWithoutFields() throws JsonProcessingException {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(
                "", "",
                null, null,
                null);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        testLogger.addAppender(listAppender);
        responseLogger.log(response);

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(1, logsList.size());
        assertEquals("RESPONSE: " + objectMapper.writeValueAsString(response),
                logsList.get(0).getMessage());
        assertEquals(Level.INFO, logsList.get(0).getLevel());
    }
}