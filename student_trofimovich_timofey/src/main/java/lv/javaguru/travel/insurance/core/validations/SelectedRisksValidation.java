package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
class SelectedRisksValidation extends TravelRequestValidationImpl {
    @Autowired
    ValidationErrorFactory factory;
    @Autowired
    ClassifierValueRepository classifierValueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(factory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        List<String> selectedRisks = request.getSelectedRisks();
        if (selectedRisks == null) {
            return Collections.emptyList();
        }
        selectedRisks.forEach(r -> {
            Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                    .findByClassifierTitleAndIc("RISK_TYPE", r);
            if (optionalClassifierValue.isEmpty()) {
                errors.add(factory.buildError("ERROR_CODE_9", List.of(new Placeholder("name", r))));
            }
        });
        return errors;
    }
}
