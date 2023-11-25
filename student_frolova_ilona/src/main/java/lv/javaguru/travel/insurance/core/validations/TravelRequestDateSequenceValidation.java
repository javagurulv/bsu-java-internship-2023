package lv.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
public class TravelRequestDateSequenceValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null)
            return Optional.empty();

        return (request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime() < 0)
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after agreementDateFrom!"))
                : Optional.empty();
    }
}
