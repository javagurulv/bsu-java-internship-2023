package lv.javaguru.travel.insurance.core.processor;

import lv.javaguru.travel.insurance.core.request.processor.TravelCalculatePremiumRequestProcessor;
import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestProcessorTest {

    @Mock
    DateService dateService;

    @Mock
    TravelCalculatePremiumRequestValidator validator;

    @InjectMocks
    TravelCalculatePremiumRequestProcessor travelCalculatePremiumRequestProcessor;

    @Test
    void testProcessorValidatedResponseClean() {
        when(validator.validate(any())).thenReturn(new ArrayList<>());
        when(dateService.getDaysBetween(any(), any())).thenReturn(BigDecimal.ONE);
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().build();

        assertNull(travelCalculatePremiumRequestProcessor.buildResponse(request).getErrorList());
    }

    @Test
    void testProcessorValidatedResponseWithOneError() {
        var expectedErrors = new ArrayList<>(
                List.of(
                        new ValidationError("someField", "error")
                )
        );

        when(validator.validate(any())).thenReturn(
                expectedErrors
        );

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().build();

        assertEquals(expectedErrors, travelCalculatePremiumRequestProcessor.buildResponse(request).getErrorList());
    }

    @Test
    void testProcessorValidatedResponseWithErrorSequence() {
        var expectedErrors = new ArrayList<>(
                List.of(
                        new ValidationError("someField1", "error1"),
                        new ValidationError("someField2", "error2")
                )
        );

        when(validator.validate(any())).thenReturn(
                expectedErrors
        );

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder().build();

        assertEquals(expectedErrors, travelCalculatePremiumRequestProcessor.buildResponse(request).getErrorList());
    }

}
