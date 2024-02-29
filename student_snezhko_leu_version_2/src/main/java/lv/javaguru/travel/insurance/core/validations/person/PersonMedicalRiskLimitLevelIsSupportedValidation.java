package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.core.validations.agreement.TravelAgreementFieldValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonMedicalRiskLimitLevelIsSupportedValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if (person.getMedicalRiskLimitLevel() == null || person.getMedicalRiskLimitLevel().isEmpty()) {
            return Optional.empty();
        }
        return !existMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }

    private boolean existMedicalRiskLimitLevelIc(String mrllIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", mrllIc)
                .isPresent();
    }
}
