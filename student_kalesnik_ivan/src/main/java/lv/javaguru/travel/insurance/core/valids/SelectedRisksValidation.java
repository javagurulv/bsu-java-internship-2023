package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SelectedRisksValidation implements TravelRequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelected_risks() == null || request.getSelected_risks().isEmpty())
                ? Optional.of(new ValidationError("selectedRisks", "Must not be empty!"))
                : Optional.empty();
    }
}
