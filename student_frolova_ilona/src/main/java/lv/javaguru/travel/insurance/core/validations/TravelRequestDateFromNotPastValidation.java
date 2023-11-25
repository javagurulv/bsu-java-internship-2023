package lv.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
@Getter
class TravelRequestDateFromNotPastValidation implements TravelRequestValidation {

    private final long allowedDelayFromPresent = 5000L;

    private final long millisecondsNow =  LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) return Optional.empty();

        return (request.getAgreementDateFrom().getTime() + allowedDelayFromPresent < millisecondsNow)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be from the past!"))
                : Optional.empty();
    }
}
