package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgreementDateToValidator implements TravelRequestValidation{

    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(factory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
