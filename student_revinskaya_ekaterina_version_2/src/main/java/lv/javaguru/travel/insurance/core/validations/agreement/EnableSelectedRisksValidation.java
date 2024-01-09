package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EnableSelectedRisksValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Value("${cancellation.risk.enable:false}")
    private Boolean cancellationRiskEnable;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return (request.getSelectedRisks() != null && request.getSelectedRisks().contains("TRAVEL_CANCELLATION")
        && !cancellationRiskEnable)
                ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_22"))
                : Optional.empty();
    }

}
