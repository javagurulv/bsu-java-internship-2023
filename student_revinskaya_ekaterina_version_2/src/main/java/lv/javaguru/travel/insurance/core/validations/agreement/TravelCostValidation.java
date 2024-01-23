package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation.TravelCancellationRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class TravelCostValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        String cancellationRiskIc = "TRAVEL_CANCELLATION";
        return  request.getSelectedRisks() != null
                && request.getSelectedRisks().contains(cancellationRiskIc) && request.getTravelCost() == null
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_21"))
                : Optional.empty();
    }
}
