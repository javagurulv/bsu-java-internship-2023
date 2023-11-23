package lv.javaguru.travel.insurance.core.valids;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyCountryValidation extends TravelRequestValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (containsTravelMedical(request) && countryIsNullOrBlank(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    private boolean containsTravelMedical(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks() != null
                && request.getSelected_risks().contains("TRAVEL_MEDICAL");
    }

    private boolean countryIsNullOrBlank(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

}
