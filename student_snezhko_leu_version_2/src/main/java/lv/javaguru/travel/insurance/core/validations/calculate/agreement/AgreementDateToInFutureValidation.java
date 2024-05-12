package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AgreementDateToInFutureValidation extends TravelAgreementFieldValidationImpl {

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return request.getAgreementDateTo() != null && request.getAgreementDateTo().before(new Date())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        return null;
    }
}
