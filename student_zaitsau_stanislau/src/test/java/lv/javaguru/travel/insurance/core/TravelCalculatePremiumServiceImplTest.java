package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class TravelCalculatePremiumServiceImplTest {
    private final DateFormat dateFormat = DateTimeService.getDateFormat();
    private final TravelCalculatePremiumService service = new TravelCalculatePremiumServiceImpl();
    @ParameterizedTest
    @CsvSource({
            "Stanislau, Zaitsau, 21.10.2012, 10.11.2012",
            "Daria, Glovackaya, 20.10.2023, 01.01.2024",
    })
    public void fillResponseTest(String firstName, String lastName, String dateFrom, String dateTo)
        throws ParseException {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            firstName,
            lastName,
            this.dateFormat.parse(dateFrom),
            this.dateFormat.parse(dateTo)
            );

        TravelCalculatePremiumResponse response = this.service.calculatePremium(request);

        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
        Assertions.assertEquals(request.getPersonLastName(), response.getPersonLastName());
        Assertions.assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
        Assertions.assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
        Assertions.assertEquals(DateTimeService.getDaysDifference(request.getAgreementDateFrom(), response.getAgreementDateTo()), response.getAgreementPrice());
    }

}