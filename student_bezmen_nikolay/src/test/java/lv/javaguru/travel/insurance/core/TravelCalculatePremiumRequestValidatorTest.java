package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelRequestValidation;
import lv.javaguru.travel.insurance.core.validations.ValidateAgreementDateFrom;
import lv.javaguru.travel.insurance.core.validations.ValidateAgreementDateTo;
import lv.javaguru.travel.insurance.core.validations.ValidatePersonLastName;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Test
    void testValidatorIfAllGood() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validateAgreementDateFrom = mock(ValidateAgreementDateFrom.class);
        TravelRequestValidation validateAgreementDateTo = mock(ValidateAgreementDateTo.class);
        TravelRequestValidation validatePersonLastName = mock(ValidatePersonLastName.class);
        when(validateAgreementDateFrom.execute(request)).thenReturn(Optional.empty());
        when(validateAgreementDateTo.execute(request)).thenReturn(Optional.empty());
        when(validatePersonLastName.execute(request)).thenReturn(Optional.empty());

        requestValidator = new TravelCalculatePremiumRequestValidator(List.of(validatePersonLastName,
                validateAgreementDateTo,validateAgreementDateFrom));

        assertThat(requestValidator.validate(request).size()).isEqualTo(0);
    }

    @Test
    void testValidatorIfExistErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validateAgreementDateFrom = mock(ValidateAgreementDateFrom.class);
        TravelRequestValidation validateAgreementDateTo = mock(ValidateAgreementDateTo.class);
        TravelRequestValidation validatePersonLastName = mock(ValidatePersonLastName.class);
        when(validateAgreementDateFrom.execute(request)).thenReturn(Optional.of(new ValidationError()));
        when(validateAgreementDateTo.execute(request)).thenReturn(Optional.of(new ValidationError()));
        when(validatePersonLastName.execute(request)).thenReturn(Optional.of(new ValidationError()));

        requestValidator = new TravelCalculatePremiumRequestValidator(List.of(validatePersonLastName,
                validateAgreementDateTo,validateAgreementDateFrom));

        assertThat(requestValidator.validate(request).size()).isEqualTo(3);
    }
}
