package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation.TravelRiskPremiumCalculatorCancellation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
class TravelRiskPremiumCalculatorCancellationTest {
    TravelRiskPremiumCalculatorCancellation calculator = new TravelRiskPremiumCalculatorCancellation();
    @Test
    public void calculatePremiumTest() {
        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_CANCELLATION")
                .build();
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.ZERO);
    }

    private Date createDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}
