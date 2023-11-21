package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.ErrorCodesPropertiesReader;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
class TravelRequestAgreementDateToOfFutureValidation implements TravelRequestValidation{
    @Autowired
    private DateTimeService dateTimeService;
    @Autowired
    private ErrorCodesPropertiesReader reader;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && (dateTimeService.getCurrentDateTime().after(dateTo)))
                ? Optional.of(constructError("ERROR_CODE_6"))
                : Optional.empty();
    }
    private ValidationError constructError(String errCode){
        return new ValidationError(errCode, reader.getDescription(errCode));
    }
}
