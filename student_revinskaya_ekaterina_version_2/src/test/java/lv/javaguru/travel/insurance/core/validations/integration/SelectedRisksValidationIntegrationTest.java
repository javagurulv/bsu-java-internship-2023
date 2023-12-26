package lv.javaguru.travel.insurance.core.validations.integration;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class SelectedRisksValidationIntegrationTest {

    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(createDate("01.01.2030"))
                .agreementDateTo(createDate("07.01.2030"))
                .country("SPAIN")
                .persons(List.of(PersonDTO.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personBirthDate(createDate("01.01.2000"))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .personalCode("7585858")
                        .build()
                )).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_8");
        assertEquals(errors.get(0).getDescription(), "Field selected_risks must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenNotExistSelectedRisk() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(createDate("01.01.2030"))
                .agreementDateTo(createDate("05.01.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("FAKE_MEDICAL"))
                .persons(List.of(PersonDTO.builder()
                        .personFirstName("Vasja")
                        .personLastName("Pupkin")
                        .personBirthDate(createDate("01.01.2000"))
                        .medicalRiskLimitLevel("LEVEL_10000")
                        .personalCode("567585")
                        .build()
                )).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_9");
        assertEquals(errors.get(0).getDescription(), "Risk Type ic = FAKE_MEDICAL not supported by the system");
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
