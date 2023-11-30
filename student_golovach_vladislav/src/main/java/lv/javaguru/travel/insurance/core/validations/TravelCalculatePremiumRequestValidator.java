package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface TravelCalculatePremiumRequestValidator {

    List<ValidationError> validate(TravelCalculatePremiumRequest request);

}