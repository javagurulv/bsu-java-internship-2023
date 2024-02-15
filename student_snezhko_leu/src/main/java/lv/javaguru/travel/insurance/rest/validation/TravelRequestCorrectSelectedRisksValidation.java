package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestCorrectSelectedRisksValidation extends TravelRequestValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private ClassifierValueRepository classifierValueRepository;

    private List<Placeholder> placeholders;
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
    public List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request) {
        List<ValidationError> result = new ArrayList<>();
        List<String> risks = request.getSelectedRisks();
        if (risks == null || risks.isEmpty()) {
            return null;
        }
        //List<Placeholder> placeholders = new ArrayList<>();
        for (String risk : risks) {
            Optional<ClassifierValue> cv = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", risk);
            if (cv.isEmpty()) {
                initPlaceholders(placeholders, risk);
                result.add(errorFactory.buildError("ERROR_CODE_9",placeholders));
                        //"ERROR_CODE_9", "Risk with ic = " + risk + " is not supported!"));//Optional.of(errorFactory.buildError("ERROR_CODE_9"));//"ERROR_CODE_9", "Risk with ic = " + risk + "is not exist"));
            }
        }
        return result;
    }

    private void initPlaceholders(List<Placeholder> placeholders, String risk) {
        Placeholder notExistingRisk = new Placeholder("NOT_EXISTING_RISK", risk);
        if (placeholders == null) {
            placeholders = new ArrayList<>();
        }
        placeholders.clear();
        placeholders.add(notExistingRisk);
        //return placeholders;
    }
}
