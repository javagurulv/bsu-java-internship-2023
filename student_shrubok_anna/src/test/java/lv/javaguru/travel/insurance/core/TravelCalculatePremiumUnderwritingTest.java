package lv.javaguru.travel.insurance.core;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumUnderwritingTest {

    @InjectMocks TravelCalculatePremiumUnderwriting underwriting;
    @Mock DateTimeService dateTimeService;

    @Test
    public void returnsCorrectPriceFromCorrectRequest()
    {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("14-04-2005"));
        when(request.getAgreementDateTo()).thenReturn(createDate("15-04-2005"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(1L);
        BigDecimal agreementPrice = underwriting.calculateAgreementPrice(request);
        assertEquals(agreementPrice, new BigDecimal(1));
    }

    Date createDate(String stringDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            return  formatter.parse(stringDate);
        }
        catch(ParseException e)
        {
            throw new RuntimeException();
        }
    }
}
