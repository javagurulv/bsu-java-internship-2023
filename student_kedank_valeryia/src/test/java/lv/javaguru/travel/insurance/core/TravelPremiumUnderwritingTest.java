package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;
    @Mock
    private DateTimeService dateTimeService;


    @Test
    public void underwritingIsPositive(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createData("01.01.2005"));
        when(request.getAgreementDateTo()).thenReturn(createData("10.01.2005"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(),
                request.getAgreementDateTo())).thenReturn(9L);
        BigDecimal premium = premiumUnderwriting.calculatePremium(request);

        assertEquals(premium, BigDecimal.valueOf(9));
    }

    private Date createData(String strData){
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(strData);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
