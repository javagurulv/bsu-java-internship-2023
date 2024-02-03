package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestCorrectCountryValueValidation extends TravelRequestValidationImpl{

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private List<Placeholder> placeholders;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        Optional<ValidationError> result = Optional.empty();
        String country = request.getCountry();

        if (country == null || country.isEmpty()) {
            return result;
        }

        Optional<ClassifierValue> classifierValue = classifierValueRepository.findByClassifierTitleAndIc("COUNTRY", country);
        if (classifierValue.isEmpty()) {
            String errorCode = "ERROR_CODE_11";
            result = Optional.of(
                errorFactory.buildError(
                        errorCode, initPlaceholder(placeholders, country)
                )
            );
        }

        return result;
    }

    private List<Placeholder> initPlaceholder(List<Placeholder> result, String incorrectCountry) {
        Placeholder placeholder = new Placeholder("{NOT_EXISTING_COUNTRY}", incorrectCountry);
        result.add(placeholder);
        return result;
    }
}
