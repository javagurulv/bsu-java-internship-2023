package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.calculate.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.calculate.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
public class PersonMedicalRiskLimitLevelIsSupportedValidationTest extends AbstractPersonFieldValidationTest {
    private String mrllIc = "mrll";

    @Mock
    private ClassifierValueRepository classifierValueRepository;

    @Mock
    private ClassifierValue classifierValue;

    void initClassifierValue(String classifierIc, String ic) {
    //    classifierValue = mock(ClassifierValue.class);

      //  classifierValueRepository = mock(ClassifierValueRepository.class);
        when(classifierValueRepository.findByClassifierTitleAndIc(classifierIc, ic)).thenReturn(Optional.empty());
        ReflectionTestUtils.setField(validation, "classifierValueRepository", classifierValueRepository);
    }
    private void init() {
        super.init("ERROR_CODE_14",
                "description",
                new PersonMedicalRiskLimitLevelIsSupportedValidation());
        initClassifierValue("MEDICAL_RISK_LIMIT_LEVEL", mrllIc);
    }

    @Test
    public void notExistingMedicalRiskLimitLevel() {
        init();
        List<Placeholder> placeholders = new ArrayList<>();
        when(errorFactory.buildError(errorCode, placeholders)).thenReturn(new ValidationErrorDTO(errorCode, description));
        ReflectionTestUtils.setField(validation, "placeholders", placeholders);
        when(person.getMedicalRiskLimitLevel()).thenReturn(mrllIc);
        Optional<ValidationErrorDTO> error = validation.validate(person);
        assertEquals("", errorCode, error.get().getErrorCode());
        assertEquals("", description, error.get().getDescription());
    }

    @Test
    public void correctMedicalRiskLimitLevelTest() {
        init();
        String correctMrll = "correctMrll";
        when(person.getMedicalRiskLimitLevel()).thenReturn(correctMrll);
        when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", correctMrll)).thenReturn(Optional.of(classifierValue));
        assertEquals("", Optional.empty(), validation.validate(person));
    }
}
