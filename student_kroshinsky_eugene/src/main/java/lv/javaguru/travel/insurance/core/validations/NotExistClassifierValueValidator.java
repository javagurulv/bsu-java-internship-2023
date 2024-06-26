package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class NotExistClassifierValueValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        ArrayList<ValidationError> result = new ArrayList<>();
        for (String riskType : request.getSelectedRisks()) {
            if (valueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskType).isEmpty()) {
                Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", riskType);
                result.add(validationErrorFactory.buildError("NOT_EXISTING_RISK", List.of(placeholder)));
            }
        }
        return result;
    }
}
