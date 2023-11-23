package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Mock
    private DateTimeUtil calculatorDate;
    @InjectMocks
    private TravelPremiumUnderwritingImpl calculateUnderwriting;
    @InjectMocks
    TravelMedicalRiskPremiumCalculator medicalRiskPremiumCalculator;

    @Test
    public void rightCalculateUnderwriting(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelected_risks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        calculateUnderwriting.riskPremiumCalculators=List.of(medicalRiskPremiumCalculator);
        when(calculatorDate.calculateDiffBetweenDays(any(), any()))
                .thenReturn(BigDecimal.valueOf(4));
        assertEquals(calculateUnderwriting.calculateAgreementPrice(request), BigDecimal.valueOf(4));
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
