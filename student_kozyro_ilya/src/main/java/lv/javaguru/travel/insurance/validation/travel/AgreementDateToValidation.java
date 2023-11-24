package lv.javaguru.travel.insurance.validation.travel;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgreementDateToValidation implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null) ?
                Optional.of(new ValidationError("agreementDateTo", "Shouldn't be empty!")) :
                Optional.empty();
    }
}
