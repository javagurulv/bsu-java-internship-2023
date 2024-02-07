package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class NotExistClassifierValueValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Autowired private ClassifierValueRepository valueRepository;
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream().
                filter(risk -> valueRepository
                        .findByClassifierTitleAndIc("RISK_TYPE", risk).isEmpty())
                .map(risk -> createError(risk)).collect(Collectors.toList());
    }
    private ValidationError createError(String risk) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", risk);
        return validationErrorFactory.buildError("NOT_EXISTING_RISK", List.of(placeholder));
    }
}
