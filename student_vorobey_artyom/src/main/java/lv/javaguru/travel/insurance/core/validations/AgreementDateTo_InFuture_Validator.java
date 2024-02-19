package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.ErrorCodeReader;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateTo_InFuture_Validator implements TravelRequestValidation{

    @Autowired
    private ValidationErrorFactory factory;

    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        Date dateNow = new Date();
        Date requestDateTo = request.getAgreementDateTo();
        return requestDateTo != null && dateNow.getTime() > requestDateTo.getTime()
                ? Optional.of(factory.buildError("ERROR_CODE_7"))
                : Optional.empty();

    }
}
