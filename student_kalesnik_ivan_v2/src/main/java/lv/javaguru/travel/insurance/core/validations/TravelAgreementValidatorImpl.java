package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public
class TravelAgreementValidatorImpl implements TravelAgreementValidator {

    @Autowired private List<AgreementFieldValidation> agreementFieldValidations;
    @Autowired private List<PersonFieldValidation> personFieldValidations;

    @Autowired private AgreementValidator agreementTravelValidator;
    @Autowired private PersonValidator personValidator;

    @Override
    public List<ValidationErrorDto> validate(AgreementDto agreement) {
        List<ValidationErrorDto> agreementErrors = agreementTravelValidator.validate(agreement);
        List<ValidationErrorDto> personErrors = personValidator.validate(agreement);
        return concatenateLists(agreementErrors, personErrors);
    }



    protected List<ValidationErrorDto> concatenateLists(List<ValidationErrorDto> singleErrors,
                                                        List<ValidationErrorDto> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }

}