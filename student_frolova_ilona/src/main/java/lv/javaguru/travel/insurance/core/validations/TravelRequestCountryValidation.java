package lv.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class TravelRequestCountryValidation extends TravelRequestValidationImpl {

    private final ValidationErrorFactory errorFactory;

    private final ClassifierValueRepository repository;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {

        String country = request.getCountry();

        if (country == null)
            return Optional.empty();

        return (repository.findByClassifierTitleAndIc("COUNTRY", country).isEmpty())
                ? Optional.of(errorFactory.buildError(
                        "ERROR_CODE_11",
                        List.of(new Placeholder("COUNTRY", country))
                ))
                : Optional.empty();
    }
}

