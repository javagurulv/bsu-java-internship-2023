package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {
    @Mock DateTimeService dateTimeService;
    @Mock TravelCalculatePremiumRequestValidator validator;
    @InjectMocks TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    void createRequest() {
        request = mock(TravelCalculatePremiumRequest.class);
    }
    @Test
    public void test_correct_set_field() {
        //TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        String firstName = "Nikolay";
        String lastName = "Bezmen";
        Date dateFrom = new Date(2002, Calendar.AUGUST, 11);
        Date dateTo = new Date(2002, Calendar.SEPTEMBER, 11);
        BigDecimal correctAgreementPrice = new BigDecimal(
                TimeUnit.DAYS.convert(
                        dateTo.getTime() -
                                dateFrom.getTime(), TimeUnit.MILLISECONDS)
        );
        when(validator.validate(request)).thenReturn(List.of());
        when(request.getPersonFirstName()).thenReturn(firstName);
        when(request.getPersonLastName()).thenReturn(lastName);
        when(request.getAgreementDateFrom()).thenReturn(dateFrom);
        when(request.getAgreementDateTo()).thenReturn(dateTo);
        when(dateTimeService.getDaysBetween(dateFrom,dateTo)).thenReturn(31L);
        TravelCalculatePremiumResponse resultOfWork = service.calculatePremium(request);

        assertThat(resultOfWork.getAgreementDateFrom()).isEqualTo(request.getAgreementDateFrom());
        assertThat(resultOfWork.getAgreementDateTo()).isEqualTo(request.getAgreementDateTo());
        assertThat(resultOfWork.getPersonFirstName()).isEqualTo(request.getPersonFirstName());
        assertThat(resultOfWork.getPersonLastName()).isEqualTo(request.getPersonLastName());
        assertThat(resultOfWork.getAgreementPrice()).isEqualTo(correctAgreementPrice);
    }

}