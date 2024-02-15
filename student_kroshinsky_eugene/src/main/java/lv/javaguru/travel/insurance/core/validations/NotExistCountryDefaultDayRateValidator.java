package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class NotExistCountryDefaultDayRateValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (isEmptyCountry(request) && containsMedicalRisk(request) && containsCountryInDB(request))
            ? Optional.of(validationErrorFactory.buildError("NOT_EXISTING_RISK",
                createListOfPlaceholders(request)))
            : Optional.empty();
    }
    private boolean containsCountryInDB(TravelCalculatePremiumRequest request) {
        return valueRepository.findByClassifierTitleAndIc("COUNTRY",
                request.getCountry()).isEmpty();
    }
    private boolean isEmptyCountry(TravelCalculatePremiumRequest request) {
        return request.getCountry()!= null && !request.getCountry().isBlank();
    }
    private boolean containsMedicalRisk(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private List<Placeholder> createListOfPlaceholders(TravelCalculatePremiumRequest request){
        String country = request.getCountry();
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", country);
        return List.of(placeholder);
    }
}

