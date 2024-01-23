package lv.javaguru.travel.insurance.core.validations.calculate.premium.integration;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.TravelAgreementValidator;
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
public class AgreementDateToValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateToIsNull() {
        AgreementDTO agreement = createAgreement()
                .withAgreementDateFrom(createDate("12.10.2030"))
                .withAgreementDateTo(null)
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(PersonDTOBuilder.createPerson()
                        .withPersonUUID("1212")
                        .withPersonFirstName("Vasja")
                        .withPersonLastName("Pupkin")
                        .withPersonBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_4");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateTo is empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateToIsInThePast() {
        AgreementDTO agreement = createAgreement()
                .withAgreementDateFrom(createDate("10.02.2000"))
                .withAgreementDateTo(createDate("12.10.2000"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(PersonDTOBuilder.createPerson()
                        .withPersonUUID("1212")
                        .withPersonFirstName("Vasja")
                        .withPersonLastName("Pupkin")
                        .withPersonBirthDate(createDate("01.01.2000"))
                        .withMedicalRiskLimitLevel("LEVEL_10000")
                ).build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(2);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_5");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateFrom is in the past!");
        assertThat(errors.get(1).getErrorCode()).isEqualTo("ERROR_CODE_6");
        assertThat(errors.get(1).getDescription()).isEqualTo("Field agreementDateTo is in the past!");
    }


    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
