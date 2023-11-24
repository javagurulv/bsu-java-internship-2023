package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    private static TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    static TravelCalculatePremiumRequest request;
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final Date DATE_FROM = createDate("2000-11-11");
    private static final Date DATE_TO = createDate("2000-12-11");

    @BeforeAll
    static void setup() {
        requestValidator = new TravelCalculatePremiumRequestValidator();
    }

    @ParameterizedTest
    @MethodSource("getErrorInicializationRequest")
    void test_correct_validation_errors(String firstName, String lastName, Date from, Date to,
                                        List<ValidationError> correctValidationErrorList) {
        request = mock(TravelCalculatePremiumRequest.class);

        when(request.getPersonFirstName()).thenReturn(firstName);
        when(request.getPersonLastName()).thenReturn(lastName);
        when(request.getAgreementDateFrom()).thenReturn(from);
        when(request.getAgreementDateTo()).thenReturn(to);

        List<ValidationError> resultOfWork = requestValidator.validate(request);

        assertThat(resultOfWork.size()).isEqualTo(correctValidationErrorList.size());
        assertThat(resultOfWork.get(0).getField()).isEqualTo(correctValidationErrorList.get(0).getField());
        assertThat(resultOfWork.get(0).getMessage()).isEqualTo(correctValidationErrorList.get(0).getMessage());
    }

    private static Stream<Arguments> getErrorInicializationRequest() {
        return Stream.of(
                Arguments.of(FIRST_NAME, LAST_NAME, DATE_FROM, null, List.of(
                        new ValidationError("agreementDateTo", "Must not be empty!"))),
                Arguments.of(FIRST_NAME, LAST_NAME, null, DATE_TO, List.of(
                        new ValidationError("agreementDateFrom", "Must not be empty!"))),
                Arguments.of(FIRST_NAME, LAST_NAME,
                        createDate("2000-11-11"),
                        createDate("2000-11-10"), List.of(
                                new ValidationError("agreementDateFrom, agreementDateTo",
                                        "dateFrom must be before dateTo"))),
                Arguments.of(FIRST_NAME, "", DATE_FROM, DATE_TO, List.of(
                        new ValidationError("personLastName", "Must not be empty!"))),
                Arguments.of(FIRST_NAME, null, DATE_FROM, DATE_TO, List.of(
                        new ValidationError("personLastName", "Must not be empty!"))),
                Arguments.of("", LAST_NAME, DATE_FROM, DATE_TO, List.of(
                        new ValidationError("personFirstName", "Must not be empty!"))),
                Arguments.of(null, LAST_NAME, DATE_FROM, DATE_TO, List.of(
                        new ValidationError("personFirstName", "Must not be empty!")))
        );
    }

    @Test
    void test_if_all_fields_is_correct() {
        TravelCalculatePremiumRequest premiumRequest =
                new TravelCalculatePremiumRequest(FIRST_NAME, LAST_NAME, DATE_FROM, DATE_TO);

        List<ValidationError> resultOfWork = requestValidator.validate(premiumRequest);

        assertThat(resultOfWork.size()).isZero();
    }

    private static java.util.Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
