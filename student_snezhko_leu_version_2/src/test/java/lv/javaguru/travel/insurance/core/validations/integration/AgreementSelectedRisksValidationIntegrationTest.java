package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.validations.integration.CreateDateUtil.createDate;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AgreementSelectedRisksValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    private String errorCode;
    private String description;

    @Test
    public void shouldReturnErrorsWhenAgreementSelectedRisksAreNull() {
        errorCode = "ERROR_CODE_6";
        description = "Field selectedRisks must not be empty!";
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2050-02-02"))
                .withDateTo(createDate("2050-04-04"))
                .withCountry("SPAIN")
                .withSelectedRisks((List<String>)null)
                .withPersons(PersonDTOBuilder.createPersonDTO()
                        .withFirstName("First")
                        .withLastName("Last")
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                        .withBirthDate(createDate("2005-02-02"))
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
                .withDateFrom(createDate("2050-02-02"))
                .withDateTo(createDate("2050-04-04"))
                .withCountry("SPAIN")
                .withSelectedRisks("MY_RISK")
                .withPersons(PersonDTOBuilder.createPersonDTO()
                        .withFirstName("First")
                        .withLastName("Last")
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                        .withBirthDate(createDate("2005-02-02"))
                        .build()
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals("", 1, errors.size());
        assertEquals("", errorCode, errors.get(0).getErrorCode());
        assertEquals("", description, errors.get(0).getDescription());


    }
}
