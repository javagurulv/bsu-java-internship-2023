package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExistMedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO) {
        return medicalRiskLevelSpecified(personDTO, agreementDTO) ?
                buildErrorByValueOfFlag(personDTO)
                : Optional.empty();
    }

    private boolean medicalRiskLevelSpecified(PersonDTO request, AgreementDTO agreementDTO) {
        return !(request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty())
                && medicalRiskSelected(agreementDTO);
    }

    boolean medicalRiskSelected(AgreementDTO agreementDTO) {
        return agreementDTO.getSelectedRisks() != null
                && agreementDTO.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private Optional<ValidationErrorDTO> buildErrorByValueOfFlag(PersonDTO personDTO) {
        return !medicalRiskLimitLevelEnabled ?
                Optional.of(buildingErrorNotEnable()) :
                notExistLimitLevel(personDTO) ?
                        Optional.of(buildingErrorNotExist(personDTO))
                        : Optional.empty();
    }

    private boolean notExistLimitLevel(PersonDTO request) {
        return classifierValueRepository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isEmpty();
    }

    private ValidationErrorDTO buildingErrorNotEnable() {
        return errorFactory.buildError("ERROR_CODE_23");
    }

    private ValidationErrorDTO buildingErrorNotExist(PersonDTO person) {
        return person.getPersonalCode() != null ?
                errorFactory.buildError("ERROR_CODE_15",
                        List.of(new Placeholder("PERSONAL_CODE", person.getPersonalCode()),
                                new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                                        person.getMedicalRiskLimitLevel())))
                : errorFactory.buildError("ERROR_CODE_15",
                List.of(new Placeholder("PERSONAL_CODE", "missing"),
                        new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                                person.getMedicalRiskLimitLevel())));
    }

}
