package lv.javaguru.travel.insurance.core.validations.calculate.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AgreementSelectedRisksValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    private String errorCode;
    private String description;

    private UUID personIc = UUID.fromString("12345678-1234-1234-1234-123456789101");
    @Test
    public void shouldReturnErrorsWhenAgreementSelectedRisksAreNull() {
        errorCode = "ERROR_CODE_6";
        description = "Field selectedRisks must not be empty!";
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(CreateDateUtil.createDate("2050-02-02"))
                .withDateTo(CreateDateUtil.createDate("2050-04-04"))
                .withCountry("SPAIN")
                .withCost(BigDecimal.ONE)
                .withSelectedRisks((List<String>)null)
                .withPersons(PersonDTOBuilder.createPersonDTO()
                        .withFirstName("First")
                        .withLastName("Last")
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                        .withBirthDate(CreateDateUtil.createDate("2005-02-02"))
                        .withIc(personIc)
                        .build()
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals("", 1, errors.size());
        assertEquals("", errorCode, errors.get(0).getErrorCode());
        assertEquals("", description, errors.get(0).getDescription());

    }

    @Test
    public void shouldReturnErrorsWhenSelectedRisksAreNotSupported() {

        errorCode = "ERROR_CODE_9";
        description = "Risk Type ic = MY_RISK not supported!";
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(CreateDateUtil.createDate("2050-02-02"))
                .withDateTo(CreateDateUtil.createDate("2050-04-04"))
                .withCountry("SPAIN")
                .withCost(BigDecimal.ONE)
                .withSelectedRisks("MY_RISK")
                .withPersons(PersonDTOBuilder.createPersonDTO()
                        .withFirstName("First")
                        .withLastName("Last")
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                        .withBirthDate(CreateDateUtil.createDate("2005-02-02"))
                        .withIc(personIc)
                        .build()
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals("", 1, errors.size());
        assertEquals("", errorCode, errors.get(0).getErrorCode());
        assertEquals("", description, errors.get(0).getDescription());


    }
}
