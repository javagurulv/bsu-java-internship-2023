package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TravelRequestExistingRisksValidation extends TravelRequestValidationImpl {

    final ValidationErrorFactory errorFactory;

    /*@Override
    public List<ValidationError> checkList(TravelCalculatePremiumRequest request) {
        for (String risk : request.getSelectedRisks()) {

        }
    }*/
}
