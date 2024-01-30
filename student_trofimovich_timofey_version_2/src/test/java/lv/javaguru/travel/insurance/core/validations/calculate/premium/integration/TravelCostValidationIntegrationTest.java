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
public class TravelCostValidationIntegrationTest {
    @Autowired
    private TravelAgreementValidator validator;

    @Test
    public void shouldReturnErrorWhenTravelCostIsNul() {
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(DateTimeUtil.createDate("12.10.2030"))
                .agreementDateTo(DateTimeUtil.createDate("14.10.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_CANCELLATION"))
                .persons(
                        List.of(PersonDTO.builder()
                                .personUUID("1212")
                                .personFirstName("Vasja")
                                .personLastName("Pupkin")
                                .personBirthDate(DateTimeUtil.createDate("01.01.2000"))
                                .build())
                )
                .build();
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertThat(errors.size()).isEqualTo(1);
        assertThat(errors.get(0).getErrorCode()).isEqualTo("ERROR_CODE_19");
        assertThat(errors.get(0).getDescription()).isEqualTo("Field travelCost is empty!");
    }

}
