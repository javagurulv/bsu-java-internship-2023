package lv.javaguru.travel.insurance.core.validations.integration;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTOBuilder;
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

import static lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTOBuilder.createAgreement;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonDateOfBirthValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateOfBirthIsNull() {
        AgreementDTO agreement = createAgreement()
                .withAgreementDateFrom(createDate("10.10.2030"))
                .withAgreementDateTo(createDate("12.10.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(PersonDTOBuilder.createPerson()
                        .withPersonFirstName("Vasja")
                        .withPersonLastName("Pupkin")
                        .withPersonBirthDate(null)
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_12");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field dateOfBirth is empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateOfBirthIsInTheFuture() {
        AgreementDTO agreement = createAgreement()
                .withAgreementDateFrom(createDate("10.10.2030"))
                .withAgreementDateTo(createDate("12.10.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(PersonDTOBuilder.createPerson()
                        .withPersonFirstName("Vasja")
                        .withPersonLastName("Pupkin")
                        .withPersonBirthDate(createDate("10.10.2030"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_13");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field dateOfBirth is from the future!");
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
