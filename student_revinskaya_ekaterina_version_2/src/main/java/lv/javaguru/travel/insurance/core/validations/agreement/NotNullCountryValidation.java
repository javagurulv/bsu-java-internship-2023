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

@Component
public class NotNullCountryValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {//can be validateList later
        return validateNotNull(request) ? Optional.of(validationErrorFactory.buildError("ERROR_CODE_10",
                List.of(new Placeholder("SELECTED_RISK", "TRAVEL_MEDICAL"))))
                : Optional.empty();
    }

    private boolean validateNotNull(AgreementDTO request) {
        return (request.getCountry() == null || request.getCountry().isEmpty());
    }
}