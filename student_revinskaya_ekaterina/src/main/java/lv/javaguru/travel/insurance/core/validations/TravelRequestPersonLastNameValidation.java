package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class TravelRequestPersonLastNameValidation implements TravelRequestValidation {
    @Autowired
    private ErrorCodesPropertiesReader reader;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(constructError("ERROR_CODE_2"))
                : Optional.empty();
    }
    private ValidationError constructError(String errCode){
        return new ValidationError(errCode, reader.getDescription(errCode));
    }
}
