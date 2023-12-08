package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SelectedRisksValidation implements TravelRequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null && !request.getSelectedRisks().isEmpty() ?
        Optional.empty():
        Optional.of(new ValidationError("selected_risks", "Should be at least one selected risk and not empty"));
    }
}
