package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TravelCalculatePremiumServiceImplTest {
    @Autowired
    private TravelCalculatePremiumServiceImpl service;

    public TravelCalculatePremiumRequest createObjectRequest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("personFirstName",
                "personLastName",
                dateFrom, dateTo);

        return request;
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), "personFirstName");
    }
    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), "personLastName");
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();

        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateTo() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = createObjectRequest();
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(365));
    }

    //разница между двумя датами
    @Test
    public void shouldReturnZeroAgreementPrice(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = createObjectRequest();
        request.setAgreementDateFrom(dateFrom);
        request.setAgreementDateTo(dateFrom);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(0));
    }

    @Test
    public void shouldReturnPositiveAgreementPrice(){
        TravelCalculatePremiumRequest request = createObjectRequest();

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(365));
    }

    @Test
    public void shouldReturnNegativeAgreementPrice(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date dateFrom = calendar.getTime();
        calendar.set(2022, Calendar.JANUARY, 1);
        Date dateTo = calendar.getTime();

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("personFirstName",
                "personLastName", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(-365));
    }
}