package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculateMedicalDayCountTest {
    private TravelCalculateDayCount calculator = new TravelCalculateDayCount();

    private AgreementDTO agreement = mock(AgreementDTO.class);
    private PersonDTO person = mock(PersonDTO.class);

    private DateTimeUtil dateTimeUtil;
    @Test
    public void calculatorMedicalDayCountTest() {
        init();
        assertEquals(3, calculator.calculatePremium(agreement, person));
    }
    private void init() {

        //Date dateTo = new Date();
        //Date dateFrom = new Date(dateTo.getYear(), dateTo.getMonth(), dateTo.getDay() - dayCount);
        //dateFrom.setDate(dateFrom.getDay() - dayCount);


        dateTimeUtil = mock(DateTimeUtil.class);
        when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2030-05-05"));
        when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2030-05-02"));
        when(dateTimeUtil.getDaysBetween(agreement.getAgreementDateTo(), agreement.getAgreementDateFrom())).thenReturn(3L);

        ReflectionTestUtils.setField(calculator, "dateTimeUtil", dateTimeUtil);
    }
}
