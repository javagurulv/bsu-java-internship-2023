package lv.javaguru.travel.insurance.core.valids;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyCountryValidation extends TravelRequestValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;


    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (countryIsNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean countryIsNullOrBlank(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

}
