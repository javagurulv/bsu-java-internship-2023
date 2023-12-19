package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DayCountCalculatorTest {
    @Mock
    DateTimeUtil dateTimeUtil;
    @InjectMocks DayCountCalculator dayCountCalculator;
    @Test
    void shouldReturnCorrectNumberOfDays() {
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(10L);
        long numberOfDays = dayCountCalculator.getNumberOfDays(request);
        assertThat(numberOfDays).isEqualTo(10L);
    }

}
