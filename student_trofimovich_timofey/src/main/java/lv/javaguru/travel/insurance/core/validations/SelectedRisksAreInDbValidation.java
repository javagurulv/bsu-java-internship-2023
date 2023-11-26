package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class SelectedRisksAreInDbValidation extends TravelRequestValidationImpl{
    /*@Autowired ClassifierValueRepository classifierValueRepository;
    @Autowired
    ValidationErrorFactory factory;
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        List<String> selectedRisks = request.getSelectedRisks();
        selectedRisks.forEach(r -> {
            Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                    .findByClassifierTitleAndIc("RISK_TYPE", r);
            if (optionalClassifierValue.isEmpty()) {
                errors.add(factory.buildError("ERROR_CODE_9"));
            }
        });
        return errors;
    }*/
}
