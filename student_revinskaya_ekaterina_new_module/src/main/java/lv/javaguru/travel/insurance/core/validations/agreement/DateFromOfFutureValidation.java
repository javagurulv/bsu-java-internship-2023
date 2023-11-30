package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class DateFromOfFutureValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private DateTimeUtil dateTimeUtil;
@Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && (dateTimeUtil.getCurrentDateTime().after(dateFrom)))
                ? Optional.of( validationErrorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
