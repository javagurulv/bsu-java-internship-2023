package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UuidInCorrectPatternValidationTest {
    @InjectMocks
    private UuidInCorrectPatternValidation validation;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Test
    public void testCase1() {
        String uuid = "365d35b757a74428b1f26c235b6d4462";
        returnError(uuid);
    }
    @Test
    public void testCase2() {
        String uuid = "365d35b7-4428-b1f2-6c235b6d4462";
        returnError(uuid);
    }
    @Test
    public void testCase3() {
        String uuid = "365d35ba--4428-b1f2-6c235b6d4462";
        returnError(uuid);
    }

    public void returnError(String uuid) {
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_19"), anyList())).thenReturn(error);
        assertTrue(validation.validate(uuid).isPresent());
        assertEquals(validation.validate(uuid).get(), error);
    }
    @Test
    public void returnNoError() {
        String uuid = "365d35b7-57a7-4428-b1f2-6c235b6d4462";
        assertTrue(validation.validate(uuid).isEmpty());
    }

}
