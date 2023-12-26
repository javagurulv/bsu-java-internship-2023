package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
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
public class MedicalRiskLimitLevelValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    ValidationErrorFactory factory;
    @Autowired
    ClassifierValueRepository repository;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        Optional<ValidationErrorDTO> emptyError = validateEmpty(agreement);
        return emptyError.isPresent() ? emptyError : validateLimitIsSupported(agreement);
    }


    private Optional<ValidationErrorDTO> validateLimitIsSupported(AgreementDTO agreement) {
        Optional<ClassifierValue> optional = repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", agreement.getMedicalRiskLimitLevel());
        return (optional.isPresent())
                ? Optional.empty()
                : Optional.of(factory.buildError("ERROR_CODE_15",
                List.of(new Placeholder("value", agreement.getMedicalRiskLimitLevel()))));
    }

    private Optional<ValidationErrorDTO> validateEmpty(AgreementDTO request) {
        return (request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank())
                ? Optional.of(factory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }


}
