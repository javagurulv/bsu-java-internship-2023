package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingImplTest {
    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("RISK_1", "RISK_2"));
        TravelMedicalPremiumCalculation mock1 = mock(TravelMedicalPremiumCalculation.class);
        when(mock1.getRiskIc()).thenReturn("RISK_1");
        when(mock1.calculatePremium(request)).thenReturn(new BigDecimal(10));
        TravelMedicalPremiumCalculation mock2 = mock(TravelMedicalPremiumCalculation.class);
        when(mock2.getRiskIc()).thenReturn("RISK_2");
        when(mock2.calculatePremium(request)).thenReturn(new BigDecimal(10));
        List<TravelMedicalPremiumCalculation> premiumCalculationList = List.of(mock1, mock2);
        ReflectionTestUtils.setField(premiumUnderwriting, "premiumCalculationList", premiumCalculationList);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);
        assertThat(premium).isEqualTo(new BigDecimal(20));
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
