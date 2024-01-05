package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.rest.InsurancePremiumRisk;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestCorrectSelectedRisksValidation extends TravelRequestValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private ClassifierValueRepository classifierValueRepository;

    /*
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Optional<ValidationError> result = Optional.empty();
        if (request.getSelected_risks() == null || request.getSelected_risks().isEmpty()) {
            return result;
        }
        for (String risk : request.getSelected_risks()) {
            Optional<ClassifierValue> cv = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", risk);
            if (cv.isEmpty()) {
                result = Optional.of(errorFactory.buildError("ERROR_CODE_9"));//"ERROR_CODE_9", "Risk with ic = " + risk + "is not exist"));
            }
        }

        return result;
    }


     */

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<ValidationError> result = new ArrayList<>();
        List<String> risks = request.getSelected_risks();
        if (risks == null || risks.isEmpty()) {
            return null;
        }
        for (String risk : risks) {
            Optional<ClassifierValue> cv = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", risk);
            if (cv.isEmpty()) {
                result.add(new ValidationError("ERROR_CODE_9", "Risk with ic = " + risk + " is not supported!"));//Optional.of(errorFactory.buildError("ERROR_CODE_9"));//"ERROR_CODE_9", "Risk with ic = " + risk + "is not exist"));
            }
        }
        return result;
    }
}
