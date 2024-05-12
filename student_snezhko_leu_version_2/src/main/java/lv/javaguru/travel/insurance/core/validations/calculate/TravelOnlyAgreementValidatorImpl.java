package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelOnlyAgreementValidatorImpl implements TravelOnlyAgreementValidator{
    @Autowired
    List<TravelAgreementFieldValidation> agreementValidations;

    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> result = new ArrayList<>();

        agreementValidations.forEach(validation -> {
            Optional<ValidationErrorDTO> error = validation.validate(agreement);
            error.ifPresent(result::add);
            List<ValidationErrorDTO> tmp = validation.validateList(agreement);
            if (tmp != null)
            {
                result.addAll(tmp);
            }
        });

        return result;
    }
}
