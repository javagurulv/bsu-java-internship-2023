package lv.javaguru.travel.insurance.core.underwriting.integration.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.junit.jupiter.api.Disabled;
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

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreement;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"medical.risk.age.coefficient.enabled=true"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AgeCoefficientSwitchEnabledIntegrationTest {

    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;


    @Test
    public void shouldBeEnabledMedicalRiskLimitLevel() {
        PersonDTO person = createPersonDTO()
                .withFirstName("Vasja")
                .withLastName("Pupkin")
                .withBirthDate(createDate("01.01.2000"))
                .withMedicalRiskLimitLevel("LEVEL_20000")
                .build();

        AgreementDTO agreement = createAgreement()
                .withDateFrom(createDate("01.01.2030"))
                .withDateTo(createDate("01.05.2030"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();

        TravelPremiumCalculationResult result = premiumUnderwriting.calculatePremium(agreement, person);

        assertEquals(result.getTotalPremium(), new BigDecimal("490.88"));
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
