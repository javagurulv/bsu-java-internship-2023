package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementFieldValidation;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.validations.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AgreementDateFromValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    private String errorCode;
    private String description;
    @Test
    public void shouldReturnErrorsWhenDateFromIsNull() throws RuntimeException{
        errorCode = "ERROR_CODE_2";
        description = "Field agreementDateFrom must not be empty!";
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(null)
                .withDateTo(createDate("2030-04-04"))
                .withCountry("SPAIN")
                .withPremium(BigDecimal.valueOf(1))
                .withSelectedRisks("TRAVEL_MEDICAL")
                .withPersons(
                        PersonDTOBuilder.createPersonDTO()
                                .withFirstName("first")
                                .withLastName("last")
                                .withBirthDate(createDate("2005-02-02"))
                                .withMedicalRiskLimitLevel("LEVEL_10000")
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals(errorCode, errors.get(0).getErrorCode());
        assertEquals(description, errors.get(0).getDescription());
    }

    @Test
    public void shouldReturnErrorsWhenDateFromIsInThePast() {
       errorCode = "ERROR_CODE_1";
        description = "Field agreementDateFrom must be in the future!";
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2010-02-02"))
                .withDateTo(createDate("2030-04-04"))
                .withCountry("SPAIN")
                .withPremium(BigDecimal.valueOf(1))
                .withSelectedRisks("TRAVEL_MEDICAL")
                .withPersons(
                        PersonDTOBuilder.createPersonDTO()
                                .withFirstName("first")
                                .withLastName("last")
                                .withBirthDate(createDate("2005-02-02"))
                                .withMedicalRiskLimitLevel("LEVEL_10000")
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(1, errors.size());
        assertEquals(errorCode, errors.get(0).getErrorCode());
        assertEquals(description, errors.get(0).getDescription());


    }
}
