package lv.javaguru.travel.insurance.core.validations.calculate.premium.agreement;


import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DateFromValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        return (agreement.getAgreementDateFrom() == null)
                ? Optional.of(factory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }
}
