package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TravelRequestExistingRisksValidation extends TravelRequestValidationImpl {

    final ValidationErrorFactory errorFactory;
    final ClassifierValueRepository repository;

    @Override
    public List<ValidationError> checkList(TravelCalculatePremiumRequest request) {

        if (request.getSelectedRisks() == null) return List.of();

        List<ValidationError> errors = new ArrayList<>();

        for (String risk : request.getSelectedRisks()) {
            if (!existsInDatabase(risk)) {
                errors.add(
                        errorFactory.buildError(
                                "ERROR_CODE_9",
                                List.of(new Placeholder("RISK_IC", risk))
                        )
                );
            }
        }

        return errors;
    }

    private boolean existsInDatabase(String riskIC) {
        return repository.findByClassifierTitleAndIc("RISK_TYPE", riskIC).isPresent();
    }
}
