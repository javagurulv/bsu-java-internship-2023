package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierRepository;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.MedicalRiskLimitLevelEnabledUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestExistMedicalRiskLimitLevelValidation extends TravelRequestValidationImpl{
    @Autowired
    ValidationErrorFactory errorFactory;
    @Autowired
    MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
    @Autowired
    ClassifierValueRepository classifierValueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request){
        return medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()
                && medicalRiskNotEmptyOrNull(request)
                && notExistLimitLevel(request) ?
                Optional.of( buildError(request)) : Optional.empty();
    }
    private boolean medicalRiskNotEmptyOrNull(TravelCalculatePremiumRequest request) {
        return !(request.getMedicalRiskLimitLevel()==null || request.getMedicalRiskLimitLevel().isEmpty());
    }
    private ValidationError buildError(TravelCalculatePremiumRequest request){
        return errorFactory.buildError("ERROR_CODE_15", List.of(
                        new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                                request.getMedicalRiskLimitLevel())));

    }
    private boolean notExistLimitLevel(TravelCalculatePremiumRequest request){
        return classifierValueRepository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isEmpty();
    }
}
