package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class MedicalRiskLimitLevelValidation extends PersonFieldValidationImpl {

    @Autowired private ClassifierValueRepository classifierValueRepository;
    @Autowired private ValidationErrorFactory errorFactory;



    @Override
    public Optional<ValidationErrorDto> validate(PersonDto person,AgreementDto agreementDto) {
        return (isMedicalRiskLimitLevelNotBlank(person))
                && !existInDatabase(person.getMedicalRiskLimitLevel())
                ? Optional.of(buildValidationError(person.getMedicalRiskLimitLevel()))
                : Optional.empty();
    }

    private ValidationErrorDto buildValidationError(String medicalRiskLimitLevel) {
        Placeholder placeholder = new Placeholder("NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL", medicalRiskLimitLevel);
        return errorFactory.buildError("ERROR_CODE_14", List.of(placeholder));
    }

    private boolean isMedicalRiskLimitLevelNotBlank(PersonDto person) {
        return person.getMedicalRiskLimitLevel() != null && !person.getMedicalRiskLimitLevel().isBlank();
    }

    private boolean existInDatabase(String medicalRiscLimitLevelIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", medicalRiscLimitLevelIc).isPresent();
    }

}
