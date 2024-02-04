package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class TravelRequestBirthDateIsPastValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory errorFactory;

    private final DateTimeUtil dateTimeUtil;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {

        if (request.getPersonBirthDate() == null) return Optional.empty();

        return (request.getPersonBirthDate().after(dateTimeUtil.getCurrentDateTime()))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }
}