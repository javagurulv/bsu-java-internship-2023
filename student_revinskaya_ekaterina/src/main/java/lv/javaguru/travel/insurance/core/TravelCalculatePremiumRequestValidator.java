package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
class TravelCalculatePremiumRequestValidator {

    @Autowired
    List<TravelRequestValidation> travelRequestValidations;


    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        for(TravelRequestValidation validation: travelRequestValidations){
            validation.validate(request).ifPresent(errors::add);
        }
        return errors;
    }







}
