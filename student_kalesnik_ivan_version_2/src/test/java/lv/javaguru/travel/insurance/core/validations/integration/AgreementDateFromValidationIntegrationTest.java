package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreement;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateFromValidationIntegrationTest {

    @Autowired private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDTO agreement = createAgreement()
                .withDateFrom(null)
                .withDateTo(createDate("01.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(createPersonDTO()
                        .withFirstName("Vasja")
                        .withLastName("Pupkin")
                        .withPersonCode("123456-12345")
                        .withBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        AgreementDTO agreement = createAgreement()
                .withDateFrom(createDate("01.01.2020"))
                .withDateTo(createDate("01.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(createPersonDTO()
                        .withFirstName("Vasja")
                        .withLastName("Pupkin")
                        .withPersonCode("123456-12345")
                        .withBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_1");
        assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom must be in the future!");
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
