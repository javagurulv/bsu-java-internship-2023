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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @Mock DateTimeService dateTimeService;
    @InjectMocks TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Test
    void test_return_on_correct_response(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2022-10-15"));
        when(request.getAgreementDateFrom()).thenReturn(createDate("2022-10-17"));
        when(dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .thenReturn(2L);

        BigDecimal resultOfWork = travelPremiumUnderwriting.calculatePremium(request);

        assertThat(resultOfWork).isEqualTo(resultOfWork);

    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
