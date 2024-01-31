package lv.javaguru.travel.insurance.core.validations.calculate.premium.integration;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.calculate.premium.TravelAgreementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementDateToValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateToIsNull() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(DateTimeUtil.createDate("12.10.2030"))
                .agreementDateTo(null)
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(
                        List.of(PersonDTO.builder()
                                .personUUID("1212")
                                .personFirstName("Vasja")
                                .personLastName("Pupkin")
                                .personBirthDate(DateTimeUtil.createDate("01.01.2000"))
                                .medicalRiskLimitLevel("LEVEL_10000")
                                .build())
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_4");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateTo is empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateToIsInThePast() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(DateTimeUtil.createDate("08.10.2012"))
                .agreementDateTo(DateTimeUtil.createDate("10.10.2012"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(
                        List.of(PersonDTO.builder()
                                .personUUID("1212")
                                .personFirstName("Vasja")
                                .personLastName("Pupkin")
                                .personBirthDate(DateTimeUtil.createDate("01.01.2000"))
                                .medicalRiskLimitLevel("LEVEL_10000")
                                .build())
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(2);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_5");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateFrom is in the past!");
        assertThat(errors.get(1).getErrorCode()).isEqualTo("ERROR_CODE_6");
        assertThat(errors.get(1).getDescription()).isEqualTo("Field agreementDateTo is in the past!");
    }
    
}
