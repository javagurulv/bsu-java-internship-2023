package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
class TravelAgreementValidatorImpl implements TravelAgreementValidator {
    @Autowired
    private AgreementFieldValidation agreementFieldValidation;
    @Autowired
    private ListPersonFieldValidations listPersonFieldValidations;

    @Override
    public List<ValidationErrorDTO> validate(AgreementDTO request) {

        List<ValidationErrorDTO> agreementErrors = agreementFieldValidation.validateErrors(request);
        List<ValidationErrorDTO> personErrors = listPersonFieldValidations.validateErrors(request);

        return concatenateErrorLists(agreementErrors, personErrors);
    }
    private List<ValidationErrorDTO> concatenateErrorLists(
            List<ValidationErrorDTO> singleErrors, List<ValidationErrorDTO> listErrors)
    {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
