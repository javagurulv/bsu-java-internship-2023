package lv.javaguru.travel.insurance.core.validations;


//import lv.javaguru.travel.insurance.dto.ValidationError;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TravelAgreementValidatorImpl {       //will make default access mod

    @Autowired
    private TravelOnlyAgreementValidator agreementValidator;

    @Autowired
    private TravelOnlyPersonValidator personValidator;

    public List<ValidationErrorDTO> validate(AgreementDTO request) {
        List<ValidationErrorDTO> result = agreementValidator.validate(request);
        result.addAll(personValidator.validate(request));
        return result;
    }

}