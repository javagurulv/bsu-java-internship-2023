package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelCalculateClassifierValueValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        ArrayList<ValidationError> result = new ArrayList<>();
        for (String riskType : request.getSelectedRisks()) {
            if (valueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskType).isEmpty()) {
                result.add(validationErrorFactory.createValidationError("NOT_EXISTING_RISK"));
            }
        }
        return result;
    }
}
