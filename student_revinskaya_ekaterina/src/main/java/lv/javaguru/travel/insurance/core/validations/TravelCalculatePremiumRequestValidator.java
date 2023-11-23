package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public interface TravelCalculatePremiumRequestValidator {
@Autowired
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
