package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectedRiskValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        List<String> risks = request.getSelectedRisks();
        for (String ic : risks) {
            Optional<ClassifierValue> classifierValue = classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", ic);
            if (classifierValue.isEmpty()) {
                errors.add(validationErrorFactory.buildError("ERROR_CODE_9", List.of(new Placeholder("NOT_EXISTING_RISK", ic))));
            }
        }
        return errors;
    }
}
