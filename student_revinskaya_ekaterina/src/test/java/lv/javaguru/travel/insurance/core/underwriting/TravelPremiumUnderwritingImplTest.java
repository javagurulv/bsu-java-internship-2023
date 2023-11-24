package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @InjectMocks
    private TravelPremiumUnderwritingImpl travelPremiumUnderwriting;

    @Mock
    TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    public void rightCalculateUnderwriting(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(medicalRiskPremiumCalculator.calculatePremium(request)).thenReturn(BigDecimal.valueOf(4));
        when(medicalRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
       // var riskPremiumCalculators = List.of(medicalRiskPremiumCalculator);
       // ReflectionTestUtils.setField(travelPremiumUnderwriting, "riskPremiumCalculators", riskPremiumCalculators);
        travelPremiumUnderwriting.riskPremiumCalculators=List.of(medicalRiskPremiumCalculator);
        //assertEquals(calculateUnderwriting.calculatePremium(request).getTotalPremium(), BigDecimal.valueOf(4));
        assertEquals(travelPremiumUnderwriting.calculatePremium(request).getTravelRisks().size(), 1);

    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
