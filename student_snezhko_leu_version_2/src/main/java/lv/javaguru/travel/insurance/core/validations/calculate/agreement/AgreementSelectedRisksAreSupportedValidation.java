package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.calculate.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AgreementSelectedRisksAreSupportedValidation extends TravelAgreementFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private List<Placeholder> placeholders;
    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()) {
            return null;
        }
        return request.getSelectedRisks().stream()
                .map(this::validateRiskIc)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<ValidationErrorDTO> validateRiskIc(String riskIc) {
        return !existInDatabase(riskIc)
                ? Optional.of(buildError(riskIc))
                : Optional.empty();
    }

    private ValidationErrorDTO buildError(String riskIc) {
        placeholders = initPlaceholders(placeholders, riskIc);
        return errorFactory.buildError(
                "ERROR_CODE_9",
                placeholders
        );
    }
    private boolean existInDatabase(String riskIc) {
        return classifierValueRepository.findByClassifierTitleAndIc("RISK_TYPE", riskIc).isPresent();
    }

    private List<Placeholder> initPlaceholders(List<Placeholder> placeholders, String riskIc) {
        if (placeholders == null) {
            placeholders = new ArrayList<>();
            placeholders.add(new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc));
            //List.of(new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc));
            return placeholders;
        }
        if (!placeholders.stream().map(Placeholder::getPlaceholderValue).toList().contains(riskIc)) {
            placeholders.clear();
            placeholders.add(new Placeholder("NOT_EXISTING_RISK_TYPE", riskIc));
        }
        return placeholders;
    }
}
