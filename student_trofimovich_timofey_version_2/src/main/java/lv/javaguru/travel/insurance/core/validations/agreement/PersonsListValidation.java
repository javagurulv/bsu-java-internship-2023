package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonsListValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getPersons() == null || agreement.getPersons().isEmpty())
                ? Optional.of(factory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }
}
