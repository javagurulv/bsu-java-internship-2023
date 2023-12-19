package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TravelPersonFieldValidation {
    Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request);
    List<ValidationError> validateList(TravelCalculatePremiumRequestV1 request);
}
