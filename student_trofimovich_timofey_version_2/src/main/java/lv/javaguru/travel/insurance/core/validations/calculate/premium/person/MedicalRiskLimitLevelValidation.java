package lv.javaguru.travel.insurance.core.validations.calculate.premium.person;


import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
class MedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;
    @Autowired
    private ClassifierValueRepository repository;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        Optional<ValidationErrorDTO> emptyError = validateEmpty(person);
        return emptyError.isPresent() ? emptyError : validateLimitIsSupported(person);
    }


    private Optional<ValidationErrorDTO> validateLimitIsSupported(PersonDTO person) {
        Optional<ClassifierValue> optional = repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                person.getMedicalRiskLimitLevel());
        return (optional.isPresent())
                ? Optional.empty()
                : Optional.of(factory.buildError("ERROR_CODE_15",
                List.of(new Placeholder("value", person.getMedicalRiskLimitLevel()))));
    }

    private Optional<ValidationErrorDTO> validateEmpty(PersonDTO person) {
        return (person.getMedicalRiskLimitLevel() == null || person.getMedicalRiskLimitLevel().isBlank())
                ? Optional.of(factory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }


}
