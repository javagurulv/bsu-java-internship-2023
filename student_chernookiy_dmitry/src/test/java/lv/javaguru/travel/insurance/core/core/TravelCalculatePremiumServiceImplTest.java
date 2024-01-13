package lv.javaguru.travel.insurance.core.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {


    private final String TEST_FIRST_NAME = "FIRST";
    private final String TEST_LAST_NAME = "LAST";
    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;



    @Test
    public void TravelCalculatePremiumServiceImplTest() {
        Date dateTo = new Date(2002, Calendar.MARCH, 3);
        Date dateFrom = new Date(2002, Calendar.MARCH, 2);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(TEST_FIRST_NAME,
                TEST_LAST_NAME, dateFrom, dateTo);

        TravelCalculatePremiumServiceImpl travelCalculatePremiumService
                = new TravelCalculatePremiumServiceImpl(new TravelCalculatePremiumRequestValidator());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertThat(response.getPersonLastName()).isEqualTo(response.getPersonLastName());
        assertThat(response.getPersonFirstName()).isEqualTo(response.getPersonFirstName());
        assertThat(response.getAgreementDateTo()).isEqualTo(response.getAgreementDateTo());
        assertThat(response.getAgreementDateFrom()).isEqualTo(response.getAgreementDateFrom());
        assertThat(response.getAgreementPrice()).isEqualTo(new BigDecimal(1));

    }

    private static Stream<Map.Entry<Date[], BigDecimal>> dates() {
        return Stream.of(
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 2),
                        new Date(2002, Calendar.MARCH, 5)}, new BigDecimal(3)),
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 1),
                        new Date(2002, Calendar.MARCH, 3)}, new BigDecimal(2)),
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 3),
                        new Date(2002, Calendar.MARCH, 11)}, new BigDecimal(8))
        );
    }

    @ParameterizedTest
    @MethodSource("dates")
    public void TravelCalculatePremiumServiceImplAgreementPriceTest(Map.Entry<Date[], BigDecimal> dates) {

        final int INDEX_OF_TO_DATE = 1;
        final int INDEX_OF_FROM_DATE = 0;
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(TEST_FIRST_NAME,
                TEST_LAST_NAME, dates.getKey()[INDEX_OF_FROM_DATE], dates.getKey()[INDEX_OF_TO_DATE]);

        TravelCalculatePremiumServiceImpl travelCalculatePremiumService
                = new TravelCalculatePremiumServiceImpl(new TravelCalculatePremiumRequestValidator());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertThat(response.getPersonLastName()).isEqualTo(response.getPersonLastName());
        assertThat(response.getPersonFirstName()).isEqualTo(response.getPersonFirstName());
        assertThat(response.getAgreementDateTo()).isEqualTo(dates.getKey()[INDEX_OF_TO_DATE]);
        assertThat(response.getAgreementDateFrom()).isEqualTo(dates.getKey()[INDEX_OF_FROM_DATE]);
        assertThat(response.getAgreementPrice()).isEqualTo(dates.getValue());
    }


    @Test
    public void ifRequestWithErrorsShouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "field");
        assertEquals(response.getErrors().get(0).getMessage(), "errorMessage");
    }

    @Test
    public void ifRequestCorrectShouldReturnCorrectResponse() throws ParseException {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        Date dateTo = new Date();
        Date dateFrom = new Date();


        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(request)).thenReturn(List.of());

        when(request.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(request)).thenReturn(List.of());

        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(request)).thenReturn(List.of());

        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(request)).thenReturn(List.of());

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), "personFirstName");
        assertEquals(response.getPersonLastName(), "personLastName");
        assertEquals(response.getAgreementDateFrom(), dateFrom);
        assertEquals(response.getAgreementDateTo(), dateTo);
        assertEquals(response.getAgreementPrice(), new BigDecimal(0L));



    }

    private List<ValidationError> buildValidationErrorList() {
        return List.of(new ValidationError("field", "errorMessage"));
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(request)).thenReturn(errors);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertTrue(response.hasErrors());
    }
}