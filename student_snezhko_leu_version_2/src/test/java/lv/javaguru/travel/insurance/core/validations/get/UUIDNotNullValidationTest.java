package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
public class UUIDNotNullValidationTest {
    @InjectMocks
    private UUIDNotNullValidation validation;

    @Mock
    private ValidationErrorFactory errorFactory;

    private String errorCode = "ERROR_CODE_16";
    private String description = "Field uuid must be not empty!";

    private String uuid;
//    AgreementDTO agreement;
    @Test
    public void UUIDNotNull() {

  /*
        agreement = AgreementDTOBuilder.createAgreementDTO()
                .withAgreementUUID(UUID.fromString("12345678-1234-1234-1234-123456789101"))
                .build();

   */
        uuid = "12345678-1234-1234-1234-123456789101";
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));

        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isEmpty());
    }

    @Test
    public void UUIDisNull() {
//        agreement = AgreementDTOBuilder.createAgreementDTO().build();
        when(errorFactory.buildError(errorCode)).thenReturn(new ValidationErrorDTO(errorCode, description));
        Optional<ValidationErrorDTO> optional = validation.validate(uuid);
        assertTrue("", optional.isPresent());
        assertEquals("", errorCode, optional.get().getErrorCode());
        assertEquals("", description, optional.get().getDescription());
    }
}
