package lv.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import lv.javaguru.travel.insurance.core.ErrorManager;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
class TravelRequestDateSequenceValidation implements TravelRequestValidation {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null)
            return Optional.empty();

        return (request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime() < 0)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
