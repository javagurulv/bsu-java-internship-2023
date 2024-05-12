package lv.javaguru.travel.insurance.core.validations.calculate.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementFieldValidation;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class TravelAgreementFieldValidationImpl implements TravelAgreementFieldValidation {
    @Autowired
    protected ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO request) {
        return null;
    }
}
