package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestCorrectCountryValueValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestV1CountryIsCorrectValidationTest {
    @InjectMocks
    private TravelRequestCorrectCountryValueValidation validator = new TravelRequestCorrectCountryValueValidation();

    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);

    @Mock
    private ClassifierValueRepository classifierValueRepository = mock(ClassifierValueRepository.class);

    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);

    @Test
    public void TravelCalculatePremiumRequestValidatorCountryUncorrectTest() {
        when(request.getPersonFirstName()).thenReturn("First Name");
        when(request.getPersonLastName()).thenReturn("Last Name");
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-05-03"));
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-10-03"));
        List<String> risks = new ArrayList<>();
        String riskName = "TRAVEL_MEDICAL";
        //risks.add("TRAVEL_MEDICAL");
        risks.add(riskName);
        //      risks.add("UNCORRECT_RISK_2");
        when(request.getSelectedRisks()).thenReturn(risks);

        String countryName = "INCORRECT_COUNTRY";

        when(request.getCountry()).thenReturn(countryName);

        String errorCode = "ERROR_CODE_11";
        String description = "Country INCORRECT_COUNTRY is not supported!";

        List<Placeholder> placeholders = new ArrayList<>();
        Placeholder pl1 = mock(Placeholder.class);

        when(pl1.getPlaceholderName()).thenReturn("NOT_EXISTING_COUNTRY");
        when(pl1.getPlaceholderValue()).thenReturn(countryName);

        placeholders.add(pl1);

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        when(errorFactory.buildError(errorCode, placeholders)).thenReturn(new ValidationError(errorCode, description));

        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);
        ReflectionTestUtils.setField(validator, "classifierValueRepository", classifierValueRepository);
        ReflectionTestUtils.setField(validator, "placeholders", placeholders);

        Optional<ValidationError> result = validator.validate(request);
        assertTrue(isEqual(new ValidationError(errorCode, description), result.get()));
    }

    public static boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }
}
