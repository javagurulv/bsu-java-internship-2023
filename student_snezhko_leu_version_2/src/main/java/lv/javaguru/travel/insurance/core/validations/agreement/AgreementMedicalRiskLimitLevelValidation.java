package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class AgreementMedicalRiskLimitLevelValidation extends TravelAgreementFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        try {
            if (CheckApplicationPropertiesUtil.checkProperty("medical.risk.limit.level.enabled")) {
                return (agreement.getMedicalRiskLimitLevel() == null || agreement.getMedicalRiskLimitLevel().isEmpty())
                        ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                        : Optional.empty();
            }
        }
        catch (IOException ignored) {

        }
        return Optional.empty();
    }
}
