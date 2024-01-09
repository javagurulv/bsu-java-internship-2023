package lv.javaguru.travel.insurance.core.underwriting.integration.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
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

import static lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTOBuilder.createAgreement;
import static lv.javaguru.travel.insurance.core.api.dto.person.PersonDTOBuilder.createPerson;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"age.coefficient.enabled = true"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

public class AgeCoefficientSwitchEnabledIntegrationTest {

    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldBeDisabledMedicalRiskLimitLevel() {
        PersonDTO person = createPerson()
                .withPersonFirstName("Vasja")
                .withPersonLastName("Pupkin")
                .withPersonBirthDate(createDate("29.05.2000"))
                .withMedicalRiskLimitLevel("LEVEL_20000")
                .build();

        AgreementDTO agreement = createAgreement()
                .withAgreementDateFrom(createDate("25.05.2025"))
                .withAgreementDateTo(createDate("29.05.2025"))
                .withCountry("SPAIN")
                .withSelectedRisk("TRAVEL_MEDICAL")
                .withPerson(person)
                .build();

        TravelPremiumCalculationResult result = premiumUnderwriting.calculatePremium(agreement, person);

        assertEquals(result.getTotalPremium(), new BigDecimal("16.50"));
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
