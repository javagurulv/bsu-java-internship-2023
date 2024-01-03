package lv.javaguru.travel.insurance.core.validations.agreement;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class DateFromIsInFutureValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement) {
        Date dateFrom = agreement.getAgreementDateFrom();
        return (dateFrom != null && dateFrom.before(new Date()))
                ? Optional.of(factory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
