package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestBirthdayInFutureValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    DateTimeUtil dateTimeUtil;
    @Autowired
    ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO) {
        return (personDTO.getPersonBirthDate() != null && dateTimeUtil.getCurrentDateTime().before(personDTO.getPersonBirthDate()))
                ? Optional.of(buildingError(personDTO))
                : Optional.empty();
    }

    private ValidationErrorDTO buildingError(PersonDTO person) {
        return person.getPersonalCode() == null
                ? buildingErrorWithoutPersonalCode()
                : buildingErrorWithPersonalCode(person.getPersonalCode());
    }

    private ValidationErrorDTO buildingErrorWithPersonalCode(String personalCode) {
        return validationErrorFactory.buildError("ERROR_CODE_13",
                List.of( new Placeholder("PERSONAL_CODE", personalCode)));
    }

    private ValidationErrorDTO buildingErrorWithoutPersonalCode() {
        return validationErrorFactory.buildError("ERROR_CODE_13",
                List.of( new Placeholder("PERSONAL_CODE", "missing")));
    }
}
