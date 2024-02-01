package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NotExistSelectedRiskValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        return request.getSelectedRisks() != null
                ? getListValidationErrors(request)
                : List.of();
    }

    private List<ValidationErrorDTO> getListValidationErrors(AgreementDTO request) {
        return request.getSelectedRisks().stream()
                .map(this::buildError)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationErrorDTO> buildError(String ic) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", ic).isEmpty()
                ? Optional.of(validationErrorFactory.buildError
                ("ERROR_CODE_9", List.of(new Placeholder("NOT_EXISTING_RISK", ic))))
                : Optional.empty();
    }

}
