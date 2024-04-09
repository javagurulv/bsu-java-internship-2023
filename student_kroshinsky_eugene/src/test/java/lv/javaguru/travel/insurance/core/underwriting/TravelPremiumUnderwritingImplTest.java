package lv.javaguru.travel.insurance.core.underwriting;
import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingImplTest {
    @Mock private TravelRiskPremiumCalculator riskCalculator;
    @Mock private TravelCalculatePremiumRequestV1 request;
    @InjectMocks private TravelPremiumUnderwritingImpl underwriting;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(riskCalculator);
        assertNotNull(request);
        assertNotNull(underwriting);
    }
    @Test
    void calculatePremiumTest(){
        when(riskCalculator.calculatePremium(request)).thenReturn(new BigDecimal(10));
        when(riskCalculator.getRiskIc()).thenReturn("ic");
        when(request.getSelectedRisks()).thenReturn(List.of("ic"));
        List<TravelRiskPremiumCalculator> expextedList = List.of(riskCalculator);
        ReflectionTestUtils.setField(underwriting, "calculators", expextedList);
        assertEquals(new BigDecimal(10), underwriting.calculatePremium(request));
    }
}
