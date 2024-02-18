package lv.javaguru.travel.insurance.core;

import jakarta.validation.ConstraintViolationException;
import lv.javaguru.travel.insurance.config.ValidatorConfig;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static lv.javaguru.travel.insurance.utils.ConverterTestUtils.buildPojo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Import(ValidatorConfig.class)
@SpringBootTest(classes = TravelCalculatePremiumRequestValidator.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Autowired
    private TravelCalculatePremiumRequestValidator validator;

    @Test
    void validateTravelCalculatePremiumRequestShouldDoesntThrowExceptionWhenRequestValid() {
        var request = buildPojo(TravelCalculatePremiumRequest.class);

        assertDoesNotThrow(() -> validator.validateTravelCalculatePremiumRequest(request));
    }

    @Test
    void validateTravelCalculatePremiumRequestShouldThrowConstraintViolationExceptionWhenRequestInvalid() {
        var request = TravelCalculatePremiumRequest.builder().build();

        assertThrows(ConstraintViolationException.class, () -> validator.validateTravelCalculatePremiumRequest(request));
    }

    @Test
    void validateTravelCalculatePremiumRequestShouldThrowConstraintViolationExceptionWhenDateToIsNow() {
        var request = buildPojo(TravelCalculatePremiumRequest.class);
        request.setAgreementDateTo(Date.from(Instant.now()));

        assertThrows(ConstraintViolationException.class, () -> validator.validateTravelCalculatePremiumRequest(request));
    }

    @Test
    void validateTravelCalculatePremiumRequestShouldThrowConstraintViolationExceptionWhenDateFromInFuture() {
        var request = buildPojo(TravelCalculatePremiumRequest.class);
        var months = 2;
        var futureDate = LocalDate.now().plusMonths(months);
        var date = Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        request.setAgreementDateFrom(date);

        assertThrows(ConstraintViolationException.class, () -> validator.validateTravelCalculatePremiumRequest(request));
    }

}