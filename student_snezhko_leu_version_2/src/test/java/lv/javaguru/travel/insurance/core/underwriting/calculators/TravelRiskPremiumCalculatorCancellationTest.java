package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation.TravelRiskPremiumCalculatorCancellationComponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder.createPersonDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TravelRiskPremiumCalculatorCancellationTest {

    @InjectMocks
    private TravelRiskPremiumCalculatorCancellation calculator = new TravelRiskPremiumCalculatorCancellation();

    private List<TravelRiskPremiumCalculatorCancellationComponent> components;

    @Mock
    private TravelRiskPremiumCalculatorCancellationComponent component1;

    @Mock
    private TravelRiskPremiumCalculatorCancellationComponent component2;

    @Test
    public void calculatePremiumTest() {
        components = List.of(component1, component2);

        BigDecimal val1 = BigDecimal.ONE;
        BigDecimal val2 = BigDecimal.valueOf(2L);

        PersonDTO person = createPersonDTO().build();
        AgreementDTO agreement = createAgreementDTO()
                .withDateFrom(createDate("2026-09-11"))
                .withDateTo(createDate("2026-09-12"))
                .withSelectedRisks("TRAVEL_CANCELLATION")
                .build();

        when(component1.calculatePremium(agreement, person)).thenReturn(val1);
        when(component2.calculatePremium(agreement, person)).thenReturn(val2);

        ReflectionTestUtils.setField(calculator, "components", components);
        assertEquals(calculator.calculatePremium(agreement, person), BigDecimal.valueOf(3L));
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
