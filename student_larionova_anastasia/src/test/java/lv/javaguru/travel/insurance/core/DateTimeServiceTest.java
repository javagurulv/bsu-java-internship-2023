package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldDaysBetweenBeZero() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 0L);
    }

    @Test
    public void shouldDaysBetweenBePositive() {
        Date date1 = createDate("01.01.2023");
        Date date2 = createDate("10.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, 9L);
    }

    @Test
    public void shouldDaysBetweenBeNegative() {
        Date date1 = createDate("10.01.2023");
        Date date2 = createDate("01.01.2023");
        var daysBetween = dateTimeService.getDaysBetween(date1, date2);
        assertEquals(daysBetween, -9L);
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}


//package lv.javaguru.travel.insurance.core;
//
//
//import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
//import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.Objects;
//
//class TravelCalculatePremiumServiceImplTest {
//
//    @Test
//    public void Test_Step_2() {
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
//                "Ivan", "Ivanov", new Date(200L), new Date(2200L), new BigDecimal(0));
//        new TravelCalculatePremiumResponse();
//        TravelCalculatePremiumResponse response;
//        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
//        response = service.calculatePremium(request);
//        response.calculateAgreementPrice();
//        assert (Objects.equals(request.getPersonFirstName(), response.getPersonFirstName())
//                && Objects.equals(request.getPersonLastName(), response.getPersonLastName())
//                && request.getAgreementDateFrom() == response.getAgreementDateFrom()
//                && request.getAgreementDateTo() == response.getAgreementDateTo()
//                && Objects.equals(request.getAgreementPrice(), response.getAgreementPrice()));
//    }
//
//}