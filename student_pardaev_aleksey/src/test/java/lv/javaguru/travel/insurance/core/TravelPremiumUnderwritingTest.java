package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @Mock private DateTimeService dateTimeService;
    @InjectMocks
    private TravelPremiumUnderwriting underwriting;

    @Test
    void ShouldReturnCorrectAgreementPrice(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(CreateDate("24.02.2006"));
        when(request.getAgreementDateTo()).thenReturn(CreateDate("26.02.2006"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(2L);
        BigDecimal price = underwriting.calculatePremium(request);
        assertEquals(price, new BigDecimal(2));
    }

    Date CreateDate(String str){
        try{
            DateFormat temp = new SimpleDateFormat("dd.MM.yyyy");
            return temp.parse(str);
        }catch(ParseException e){
            throw new RuntimeException(e);
        }
    }
}
