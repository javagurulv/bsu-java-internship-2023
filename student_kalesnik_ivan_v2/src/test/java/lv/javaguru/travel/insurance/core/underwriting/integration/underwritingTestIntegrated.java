package lv.javaguru.travel.insurance.core.underwriting.integration;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {"medical.risk.age.coefficient.enabled=true"})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class underwritingTestIntegrated {

    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;


    @Test
    public void Test1() {
        PersonDto person = new PersonDto().builder()
                .personFirstName("Vasja")
                .personLastName("Pupkin")
                .personBirthDate(createDate("01.01.2000"))
                .medicalRiskLimitLevel("LEVEL_20000")
                .travelCost(BigDecimal.ZERO)
                .build();
        AgreementDto agreement = new AgreementDto().builder()
                .agreementDateFrom(createDate("01.01.2030"))
                .agreementDateTo(createDate("01.05.2030"))
                .country("SPAIN")
                .selectedRisks(List.of("TRAVEL_MEDICAL"))
                .persons(List.of(person))
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
