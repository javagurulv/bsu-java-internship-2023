package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidatePersonLastName implements TravelRequestValidation {
    Pattern firstAndLastNamePattern = Pattern.compile("^[A-Za-zА-Яа-я]+$");
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        String lastName = request.getPersonLastName();

        if (lastName == null || lastName.isEmpty()) {
            return Optional.of(new ValidationError("personLastName", "Must not be empty!"));
        }

        Matcher matcher = firstAndLastNamePattern.matcher(lastName);

        if (matcher.find()) {
            return Optional.empty();
        } else {
            return Optional.of(new ValidationError("personLastName", "Invalid"));
        }
    }
}
