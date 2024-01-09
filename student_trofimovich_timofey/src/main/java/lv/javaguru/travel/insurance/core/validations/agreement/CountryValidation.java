package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CountryValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    ValidationErrorFactory errorFactory;
    @Autowired
    ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
            if (countryIsEmpty(request)) {
                return Optional.of(errorFactory.buildError("ERROR_CODE_10"));
            }
            else if (!countryIsRecognised(request)) {
                return Optional.of(errorFactory.buildError("ERROR_CODE_11", List.of(new Placeholder("country", request.getCountry()))));
            }
        return Optional.empty();
    }
    private boolean countryIsEmpty(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }

    private boolean countryIsRecognised(TravelCalculatePremiumRequestV1 request) {
        String country = request.getCountry();
        return classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", country).isPresent();

    }
}