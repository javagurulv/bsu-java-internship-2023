package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;

@Component
public class TravelRequestPersonBirthDateNotNullValidation extends TravelRequestValidationImpl {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        Optional<ValidationError> result = Optional.empty();

        try {
            if (!checkProperty("medical.risk.age.enabled")) {
                return result;
            }
        }
        catch (IOException e) {

        }

        String errorCode = "ERROR_CODE_12";
        if (request.getPersonBirthDate() == null) {
            result = Optional.of(errorFactory.buildError(errorCode));
        }

        return result;
    }
}
