package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class TravelRequestBirthDateIsNotNullValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        return (request.getPersonBirthDate() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }
}