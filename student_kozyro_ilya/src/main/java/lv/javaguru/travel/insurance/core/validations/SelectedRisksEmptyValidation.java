package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.services.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static lv.javaguru.travel.insurance.core.validations.errors.ValidationErrorCodes.EMPTY_RISKS;

@Component
class SelectedRisksEmptyValidation implements TravelRequestValidation {

    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() == null ?
                Optional.empty() :
                    (!request.getSelectedRisks().isEmpty() ?
                        Optional.empty() :
                        Optional.of(validationErrorFactory.buildError(EMPTY_RISKS))
                    );
    }
}
