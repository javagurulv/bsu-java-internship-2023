package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TravelCalculatePremiumServiceImplTest {


    private final String TEST_FIRST_NAME = "FIRST";
    private final String TEST_LAST_NAME = "LAST";


    @Test
    public void TravelCalculatePremiumServiceImplTest() {
        Date dateTo = new Date(2002, Calendar.MARCH, 3);
        Date dateFrom = new Date(2002, Calendar.MARCH, 2);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(TEST_FIRST_NAME,
                TEST_LAST_NAME, dateFrom, dateTo);

        TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertThat(response.getPersonLastName()).isEqualTo(response.getPersonLastName());
        assertThat(response.getPersonFirstName()).isEqualTo(response.getPersonFirstName());
        assertThat(response.getAgreementDateTo()).isEqualTo(response.getAgreementDateTo());
        assertThat(response.getAgreementDateFrom()).isEqualTo(response.getAgreementDateFrom());
        assertThat(response.getAgreementPrice()).isEqualTo(new BigDecimal(1));

    }

    private static Stream<Map.Entry<Date[], BigDecimal>> dates() {
        return Stream.of(
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 2),
                        new Date(2002, Calendar.MARCH, 5)}, new BigDecimal(3)),
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 1),
                        new Date(2002, Calendar.MARCH, 3)}, new BigDecimal(2)),
                new AbstractMap.SimpleEntry<>(new Date[]{new Date(2002, Calendar.MARCH, 3),
                        new Date(2002, Calendar.MARCH, 11)}, new BigDecimal(8))
        );
    }

    @ParameterizedTest
    @MethodSource("dates")
    public void TravelCalculatePremiumServiceImplAgreementPriceTest(Map.Entry<Date[], BigDecimal> dates) {

        final int INDEX_OF_TO_DATE = 1;
        final int INDEX_OF_FROM_DATE = 0;
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(TEST_FIRST_NAME,
                TEST_LAST_NAME, dates.getKey()[INDEX_OF_FROM_DATE], dates.getKey()[INDEX_OF_TO_DATE]);

        TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        assertThat(response.getPersonLastName()).isEqualTo(response.getPersonLastName());
        assertThat(response.getPersonFirstName()).isEqualTo(response.getPersonFirstName());
        assertThat(response.getAgreementDateTo()).isEqualTo(dates.getKey()[INDEX_OF_TO_DATE]);
        assertThat(response.getAgreementDateFrom()).isEqualTo(dates.getKey()[INDEX_OF_FROM_DATE]);
        assertThat(response.getAgreementPrice()).isEqualTo(dates.getValue());
    }

}