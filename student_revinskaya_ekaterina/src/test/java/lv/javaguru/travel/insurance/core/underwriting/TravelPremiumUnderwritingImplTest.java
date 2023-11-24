package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelMedicalRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Test
    public void rightCalculateUnderwriting(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        SelectedRisksPremiumCalculator selectedRisksPremiumCalculator = mock(SelectedRisksPremiumCalculator.class);
        when(selectedRisksPremiumCalculator.calculateSelectedRisksPremium(request))
                .thenReturn(List.of(new TravelRisk("RISK_1",BigDecimal.valueOf(4)),
                        new TravelRisk("RISK_2",BigDecimal.valueOf(2))));
        ReflectionTestUtils.setField(travelPremiumUnderwriting, "selectedRisksPremiumCalculator", selectedRisksPremiumCalculator);
        assertEquals(travelPremiumUnderwriting.calculatePremium(request).getTotalPremium(), BigDecimal.valueOf(6));
        assertEquals(travelPremiumUnderwriting.calculatePremium(request).getTravelRisks().size(), 2);

    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
