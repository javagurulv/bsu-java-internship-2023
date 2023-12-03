package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotNullListOfPersonsValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {

        return validateNull(request)
                ? buildError()
                : Optional.empty();
    }

    private Optional<ValidationErrorDTO> buildError() {
        return Optional.of(validationErrorFactory.buildError("ERROR_CODE_16"));
    }

    private boolean validateNull(AgreementDTO request) {
        return request.getPersons() == null || request.getPersons().isEmpty();
    }
}
