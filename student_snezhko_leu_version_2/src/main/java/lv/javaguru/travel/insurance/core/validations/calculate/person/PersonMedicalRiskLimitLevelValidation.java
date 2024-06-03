package lv.javaguru.travel.insurance.core.validations.calculate.person;


import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

//import static lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementValidatorImpl.hasTravelMedicalRisk;

@Component
public class PersonMedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        try {
            if (CheckApplicationPropertiesUtil.checkProperty("medical.risk.limit.level.enabled"))
            { //&& hasTravelMedicalRisk()) {
                return (person.getMedicalRiskLimitLevel() == null || person.getMedicalRiskLimitLevel().isEmpty())
                        ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                        : Optional.empty();
            }
        }
        catch (IOException ignored) {

        }
        return Optional.empty();
    }
}
