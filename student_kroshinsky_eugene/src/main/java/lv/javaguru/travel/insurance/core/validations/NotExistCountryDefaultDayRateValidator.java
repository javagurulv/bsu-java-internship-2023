package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isEmptyCountry(request) && containsMedicalRisk(request) && containsCountryInDB(request))
            ? Optional.of(validationErrorFactory.buildError("NOT_EXISTING_RISK",
                createListOfPlaceholders(request)))
            : Optional.empty();
    }
    private boolean containsCountryInDB(TravelCalculatePremiumRequestV1 request) {
        return valueRepository.findByClassifierTitleAndIc("COUNTRY",
                request.getCountry()).isEmpty();
    }
    private boolean isEmptyCountry(TravelCalculatePremiumRequestV1 request) {
        return request.getCountry()!= null && !request.getCountry().isBlank();
    }
    private boolean containsMedicalRisk(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private List<Placeholder> createListOfPlaceholders(TravelCalculatePremiumRequestV1 request){
        String country = request.getCountry();
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", country);
        return List.of(placeholder);
    }
}

