package lv.javaguru.travel.insurance.core.validationTests;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import lv.javaguru.travel.insurance.rest.validation.TravelRequestCorrectSelectedRisksValidation;
import lv.javaguru.travel.insurance.rest.validation.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class TravelCalculatePremiumCorrectSelectedRisksValidatorTest {
    private TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
    @InjectMocks
    private TravelRequestValidation validator = new TravelRequestCorrectSelectedRisksValidation();
    @Mock
    private ValidationErrorFactory errorFactory = mock(ValidationErrorFactory.class);
    @Mock
    private ClassifierValueRepository classifierValueRepository = mock(ClassifierValueRepository.class);
    public static boolean isEqual(ValidationError e1, ValidationError e2) {
        return e1.getErrorCode().equals(e2.getErrorCode()) && e1.getDescription().equals(e2.getDescription());
    }
    @Test
    public void TravelCalculatePremiumRequestValidatorSelectedRisksUncorrectTest() {
        when(request.getPersonFirstName()).thenReturn("First Name");
        when(request.getPersonLastName()).thenReturn("Last Name");
        when(request.getAgreementDateFrom()).thenReturn(Date.valueOf("2026-05-03"));
        when(request.getAgreementDateTo()).thenReturn(Date.valueOf("2026-10-03"));
        List<String> risks = new ArrayList<>();
        //risks.add("TRAVEL_MEDICAL");
        String riskName = "UNCORRECT_RISK";
        risks.add(riskName);
  //      risks.add("UNCORRECT_RISK_2");
        when(request.getSelectedRisks()).thenReturn(risks);

        String errorCode = "ERROR_CODE_9";
        String description = "Risk with ic = UNCORRECT_RISK is not supported!";

//        String description2 = "Risk with ic = UNCORRECT_RISK_2 is not supported!";

        List<Placeholder> placeholders = new ArrayList<>();
        Placeholder pl1 = mock(Placeholder.class);

        when(pl1.getPlaceholderName()).thenReturn("NOT_EXISTING_RISK");
        when(pl1.getPlaceholderValue()).thenReturn(riskName);

        placeholders.add(pl1);

        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationError(errorCode, description));
        when(errorFactory.buildError(errorCode, placeholders)).thenReturn(new ValidationError(errorCode, description));

        ReflectionTestUtils.setField(validator, "errorFactory", errorFactory);
        ReflectionTestUtils.setField(validator, "classifierValueRepository", classifierValueRepository);
        ReflectionTestUtils.setField(validator, "placeholders", placeholders);


        List<ValidationError> error = validator.validateList(request);
        assertTrue("",isEqual(error.get(0), new ValidationError(errorCode, description)));
        //assertTrue("", isEqual(error.get(1), new ValidationError(errorCode, description2)));
    }
}
