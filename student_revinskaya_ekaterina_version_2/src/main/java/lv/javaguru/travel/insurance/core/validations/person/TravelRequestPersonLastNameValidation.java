package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
class TravelRequestPersonLastNameValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(buildError(request))
                : Optional.empty();
    }
    private ValidationErrorDTO buildError(PersonDTO personalCode) {
        String code = personalCode.getPersonalCode();
        return code == null ?
                buildErrorWithoutPersonalCode()
                : buildErrorWithPersonalCode(code);
    }
    private ValidationErrorDTO buildErrorWithPersonalCode(String personalCode) {
        return validationErrorFactory
                .buildError("ERROR_CODE_2",
                        List.of(new Placeholder("PERSONAL_CODE", personalCode.toString())));
    }
    private ValidationErrorDTO buildErrorWithoutPersonalCode() {
        return validationErrorFactory
                .buildError("ERROR_CODE_2",
                        List.of(new Placeholder("PERSONAL_CODE", "missing")));
    }
}
