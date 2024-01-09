package lv.javaguru.travel.insurance.core.validations.integration;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class AgreementDateFromValidationIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(null)
                .agreementDateTo(createDate("01.01.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(
                        PersonDTO.builder()
                                .personFirstName("Vasja")
                                .personLastName("Pupkin")
                                .personBirthDate(createDate("01.01.2000"))
                                .medicalRiskLimitLevel("LEVEL_10000")
                                .personalCode("34244242").build()
                )).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_3");
        assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom is empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(createDate("01.01.2020"))
                .agreementDateTo(createDate("01.01.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(PersonDTO.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personBirthDate(createDate("01.01.2000"))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .personalCode("3424523")
                        .build()
                )).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_5");
        assertEquals(errors.get(0).getDescription(), "Field agreementDateFrom must be future date!");
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
