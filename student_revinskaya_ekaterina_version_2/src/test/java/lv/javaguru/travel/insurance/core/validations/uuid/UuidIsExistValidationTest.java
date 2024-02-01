package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.entity.AgreementRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UuidIsExistValidationTest {
    @InjectMocks
    private UuidIsExistValidation validation;
    @Mock
    private AgreementRepository agreementRepository;
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @Test
    public void uuidIsNotExist(){
        String uuid = "365d35b7-57a7-4428-b1f2-6c235b6d4462";
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(agreementRepository.existByUuid(uuid)).thenReturn(false);
        when(validationErrorFactory.buildError(eq("ERROR_CODE_20"), anyList())).thenReturn(error);
        assertTrue(validation.validate(uuid).isPresent());
        assertEquals(validation.validate(uuid).get(), error);
    }
    @Test
    public void uuidIsExist(){
        String uuid = "365d35b7-57a7-4428-b1f2-6c235b6d4462";
        when(agreementRepository.existByUuid(uuid)).thenReturn(true);
        assertTrue(validation.validate(uuid).isEmpty());
    }
}
