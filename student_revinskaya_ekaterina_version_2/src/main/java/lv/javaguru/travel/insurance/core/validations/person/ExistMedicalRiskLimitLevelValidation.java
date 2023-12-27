package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExistMedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO) {
        return medicalRiskLevelNotEmptyOrNull(personDTO)
                && notExistLimitLevel(personDTO) ?
                Optional.of(buildingError(personDTO))
                : Optional.empty();
    }

    private boolean medicalRiskLevelNotEmptyOrNull(PersonDTO request) {
        return !(request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty());
    }
    private boolean notExistLimitLevel(PersonDTO request) {
        return classifierValueRepository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isEmpty();
    }
    private ValidationErrorDTO buildingError(PersonDTO person) {
        String code = person.getPersonalCode();
        return code == null ?
                buildErrorWithoutPersonalCode(person)
                : buildErrorWithPersonalCode(person);
    }

    private ValidationErrorDTO buildErrorWithPersonalCode(PersonDTO person) {
        return errorFactory
                .buildError("ERROR_CODE_15",
                        List.of(new Placeholder("PERSONAL_CODE", person.getPersonalCode()),
                                new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                                        person.getMedicalRiskLimitLevel())));
    }
    private ValidationErrorDTO buildErrorWithoutPersonalCode(PersonDTO person) {
        return errorFactory
                .buildError("ERROR_CODE_15",
                        List.of(new Placeholder("PERSONAL_CODE", "missing"),
                                new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                                        person.getMedicalRiskLimitLevel())));
    }
}
