package lv.javaguru.travel.insurance.core.validations.calculate.premium.agreement;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
class SelectedRisksValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory factory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(factory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        List<String> selectedRisks = agreement.getSelectedRisks();
        if (selectedRisks == null) {
            return Collections.emptyList();
        }
        selectedRisks.forEach(risk -> {
            Optional<ClassifierValue> optionalClassifierValue = classifierValueRepository
                    .findByClassifierTitleAndIc("RISK_TYPE", risk);
            if (optionalClassifierValue.isEmpty()) {
                errors.add(factory.buildError("ERROR_CODE_9", List.of(new Placeholder("name", risk))));
            }
        });
        return errors;
    }
}
