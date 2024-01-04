package lv.javaguru.travel.insurance.core.underwriting.integration.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"age.coefficient.enabled=false"})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase

public class TMAgeCoefficientCalculatorDisableCoefficientIntegrationTest {
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void AgeCoefficientDisableTest() {
        PersonDTO person = PersonDTO.builder()
                .personFirstName("Vasja")
                .personLastName("Pupkin")
                .personBirthDate(createDate("01.01.1933"))
                .medicalRiskLimitLevel("LEVEL_10000").build();
        AgreementDTO agreement = AgreementDTO.builder()
                .agreementDateFrom(createDate("01.01.2030"))
                .agreementDateTo(createDate("02.01.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(person))
                .build();

        assertEquals(premiumUnderwriting.calculatePremium(agreement, person).getTotalPremium()
                ,BigDecimal.valueOf(2.5));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
