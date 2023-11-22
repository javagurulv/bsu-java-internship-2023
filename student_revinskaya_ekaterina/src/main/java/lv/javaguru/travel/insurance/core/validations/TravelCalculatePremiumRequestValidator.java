package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator implements PublicTravelCalculatePremiumRequestValidator{

    @Autowired
    List<TravelRequestValidation> travelRequestValidations;
    @Autowired
    List<TravelRequestListValidation> travelRequestListValidations;


    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for(TravelRequestValidation validation: travelRequestValidations){
            validation.validate(request).ifPresent(errors::add);
        }
        for(TravelRequestListValidation validation: travelRequestListValidations){
            errors.addAll(validation.validate(request));
        }
        return errors;
    }







}
