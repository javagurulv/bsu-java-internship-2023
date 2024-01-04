package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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
    private DateTimeUtil dateTimeUtil;
    @InjectMocks
    private DayCountCalculator dayCountCalculator;

    @Test
    void shouldReturnCorrectNumberOfDays() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(dateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo())).thenReturn(10L);
        long numberOfDays = dayCountCalculator.getNumberOfDays(agreement);
        assertThat(numberOfDays).isEqualTo(10L);
    }

}
