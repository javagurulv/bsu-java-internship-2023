package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class TravelRequestDateSequenceValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null)
            return Optional.empty();

        return (request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime() < 0)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
