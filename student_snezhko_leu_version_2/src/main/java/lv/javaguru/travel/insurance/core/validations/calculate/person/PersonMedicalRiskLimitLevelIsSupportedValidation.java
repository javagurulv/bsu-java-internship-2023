package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.calculate.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonMedicalRiskLimitLevelIsSupportedValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private List<Placeholder> placeholders;
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if (person.getMedicalRiskLimitLevel() == null || person.getMedicalRiskLimitLevel().isEmpty()) {
            return Optional.empty();
        }
        initPlaceholders(person.getMedicalRiskLimitLevel());
        return !existMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14", placeholders))
                : Optional.empty();
    }

    private boolean existMedicalRiskLimitLevelIc(String mrllIc) {
        return classifierValueRepository
                .findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", mrllIc)
                .isPresent();
    }

    void initPlaceholders(String mrllIc) {
        if (placeholders == null) {
            placeholders = new ArrayList<>();
        }
        else {
            placeholders.clear();
        }
        placeholders.add(new Placeholder("NOT_SUPPORTED_MEDICAL_RISK_LIMIT_LEVEL", mrllIc));
    }
}
