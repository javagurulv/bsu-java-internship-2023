package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidatePersonFirstName implements TravelRequestValidation {

    Pattern firstAndLastNamePattern = Pattern.compile("^[A-Za-zА-Яа-я]+$");

    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        String firstName = request.getPersonFirstName();

        if (firstName == null || firstName.isEmpty()) {
            return Optional.of(new ValidationError("personFirstName", "Must not be empty!"));
        }

        Matcher matcher = firstAndLastNamePattern.matcher(firstName);

        if (matcher.find()) {
            return Optional.empty();
        } else {
            return Optional.of(new ValidationError("personFirstName", "Invalid"));
        }

    }
}
