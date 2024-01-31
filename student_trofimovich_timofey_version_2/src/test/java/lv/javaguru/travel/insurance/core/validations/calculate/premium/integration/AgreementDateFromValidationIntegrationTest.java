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
public class AgreementDateFromValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenDateFromIsNull() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(null)
                .agreementDateTo(DateTimeUtil.createDate("12.10.2030"))
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
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_3");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateFrom is empty!");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsInThePast() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(DateTimeUtil.createDate("12.12.2020"))
                .agreementDateTo(DateTimeUtil.createDate("12.10.2030"))
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
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_5");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateFrom is in the past!");
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(DateTimeUtil.createDate("12.12.2032"))
                .agreementDateTo(DateTimeUtil.createDate("12.10.2030"))
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
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_7");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field agreementDateFrom is not before field agreementDateTo!");
    }
    
}
