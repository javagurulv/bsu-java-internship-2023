package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {

    List<ValidationError> validate(TravelCalculatePremiumRequestV1 request);

}
