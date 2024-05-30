package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class AgreementCostValidation extends TravelAgreementFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        if (agreement.getCost() == null || agreement.getCost().equals(BigDecimal.ZERO)) {
            return Optional.of(errorFactory.buildError("ERROR_CODE_19"));
        }

        return Optional.empty();
    }
}
