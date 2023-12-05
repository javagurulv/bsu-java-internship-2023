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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static lv.javaguru.travel.insurance.core.api.dto.builders.AgreementDTOBuilder.createAgreement;
import static lv.javaguru.travel.insurance.core.api.dto.builders.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"medical.risk.limit.level.enabled=false"})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase

public class InsuranceLimitLevelCoefficientCalculatorDisableCoefficientIntegrationTest {
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void InsuranceLimitLevelCoefficientDisableTest() {
        PersonDTO person = createPersonDTO()
                .withFirstName("Vasja")
                .withLastName("Pupkin")
                .withBirthDate(createDate("01.01.2012"))
                .withMedicalRiskLimitLevel("LEVEL_50000")
                .build();
        AgreementDTO agreement = createAgreement()
                .withDateFrom(createDate("01.01.2030"))
                .withDateTo(createDate("02.01.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();
        assertEquals(premiumUnderwriting.calculatePremium(agreement, person).getTotalPremium(),
                BigDecimal.valueOf(2.5));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
