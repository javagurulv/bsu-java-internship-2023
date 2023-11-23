package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelRequestRiskValidation extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        List<String> risks = request.getSelected_risks();
        for (String ic : risks) {
            Optional<ClassifierValue> classifierValue = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", ic);
            if (classifierValue.isEmpty()) {
                errors.add(validationErrorFactory.buildError("ERROR_CODE_9", List.of(new Placeholder("NOT_EXISTING_RISK", ic))));
            }
        }
        return errors;
    }
}
