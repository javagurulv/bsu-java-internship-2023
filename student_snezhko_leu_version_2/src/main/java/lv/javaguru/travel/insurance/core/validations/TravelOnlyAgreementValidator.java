package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TravelOnlyAgreementValidator implements TravelAgreementValidator{
    @Autowired
    List<TravelAgreementFieldValidation> agreementValidations;

    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {
        List<ValidationErrorDTO> result = new ArrayList<>();

        agreementValidations.forEach(validation -> {
            Optional<ValidationErrorDTO> error = validation.validate(agreement);
            error.ifPresent(result::add);
            if (validation.validateList(agreement) != null)
            {
                result.addAll(validation.validateList(agreement));
            }
        });

        return result;
    }
}
