package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
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
public class AgreementValidator{
    @Autowired private List<AgreementFieldValidation> agreementFieldValidations;

    public List<ValidationErrorDto> validate(AgreementDto agreement) {
        List<ValidationErrorDto> singleErrors = collectSingleAgreementErrors(agreement);
        List<ValidationErrorDto> listErrors = collectListAgreementErrors(agreement);
        return concatenateLists(singleErrors, listErrors);
    }
    private List<ValidationErrorDto> collectAgreementErrors(AgreementDto agreement) {
        List<ValidationErrorDto> singleErrors = collectSingleAgreementErrors(agreement);
        List<ValidationErrorDto> listErrors = collectListAgreementErrors(agreement);
        return concatenateLists(singleErrors, listErrors);
    }

    private List<ValidationErrorDto> collectSingleAgreementErrors(AgreementDto agreement) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validate(agreement))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationErrorDto> collectListAgreementErrors(AgreementDto agreement) {
        return agreementFieldValidations.stream()
                .map(validation -> validation.validateList(agreement))
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    protected List<ValidationErrorDto> concatenateLists(List<ValidationErrorDto> singleErrors,
                                                        List<ValidationErrorDto> listErrors) {
        return Stream.concat(singleErrors.stream(), listErrors.stream())
                .collect(Collectors.toList());
    }
}
