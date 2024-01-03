package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelAgreementValidatorImpl implements TravelAgreementValidator {

    @Autowired
    private TravelPersonFieldValidator personListValidator;
    @Autowired
    private TravelAgreementFieldValidator agreementOnlyValidator;


    public List<ValidationErrorDTO> validate(AgreementDTO agreement) {

        List<ValidationErrorDTO> agreementErrors = agreementOnlyValidator.validate(agreement);
        List<ValidationErrorDTO> personErrors = personListValidator.validate(agreement);
        return concatLists(agreementErrors, personErrors);
    }


    private List<ValidationErrorDTO> concatLists(List<ValidationErrorDTO> singleErrors,
                                                 List<ValidationErrorDTO> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
