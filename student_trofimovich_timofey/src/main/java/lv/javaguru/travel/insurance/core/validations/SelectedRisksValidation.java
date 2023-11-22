package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SelectedRisksValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(new ValidationError("selectedRisks", "Must not be empty!"))
                : Optional.empty();
    }
}
