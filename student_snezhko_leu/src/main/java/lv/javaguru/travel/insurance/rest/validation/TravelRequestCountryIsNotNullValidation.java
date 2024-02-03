package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestCountryIsNotNullValidation extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        Optional<ValidationError> result = Optional.empty();
        if (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()) {
            return result;
        }
        /*
        if (!request.getSelected_risks().contains("TRAVEL_MEDICAL")) {
            return result;
        }
         */
        if (request.getCountry() == null || request.getCountry().isEmpty()) {
            String errorCode = "ERROR_CODE_10";
            result = Optional.of(errorFactory.buildError(errorCode));
        }

        return result;
    }
}
