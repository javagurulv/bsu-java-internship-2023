package lv.javaguru.travel.insurance.core.validator;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.AbstractValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TravelCalculatePremiumRequestValidator extends AbstractValidator<TravelCalculatePremiumRequest> {

    @Override
    public ArrayList<ValidationError> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        ArrayList<ValidationError> errors = new ArrayList<>();

        validateMandatoryField("personFirstName", travelCalculatePremiumRequest.getPersonFirstName()).ifPresent(errors::add);
        validateMandatoryField("personLastName", travelCalculatePremiumRequest.getPersonLastName()).ifPresent(errors::add);
        validateMandatoryField("agreementDateFrom", travelCalculatePremiumRequest.getAgreementDateFrom()).ifPresent(errors::add);
        validateMandatoryField("agreementDateTo", travelCalculatePremiumRequest.getAgreementDateTo()).ifPresent(errors::add);

        validateDatesField(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo()).ifPresent(errors::add);

        return errors;
    }

    public Optional<ValidationError> validateDatesField(Date from, Date to) {
        return ((from != null && to != null) && (from.after(to) || from.equals(to))) ?
                Optional.of(new ValidationError("agreementDayFrom", "Must be before agreementDayTo")) :
                Optional.empty();

    }



}
