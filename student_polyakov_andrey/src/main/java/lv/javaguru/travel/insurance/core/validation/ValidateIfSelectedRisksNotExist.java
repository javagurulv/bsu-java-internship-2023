package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ValidateIfSelectedRisksNotExist extends TravelRequestValidationImpl{
    @Autowired
    private  ValidationErrorFactory factory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private boolean icExistInRepo(String ic) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", ic).isPresent();
    }
    private Optional<ValidationError> createValidationError(String ic) {
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK_TYPE", ic);
        return Optional.of(factory.createError("ERROR_CODE_9", List.of(placeholder)));
    }
    private Optional<ValidationError> validateIc(String ic) {
        return !icExistInRepo(ic) ? createValidationError(ic) : Optional.empty();
    }
    private List<ValidationError> validateIfSelectedRisksNotExist(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks().stream()
                .map(this::validateIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return request.getSelected_risks() != null ? validateIfSelectedRisksNotExist(request) : List.of();
    }
}
