package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TravelCalculatePremiumServiceUnderwritingTest {

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
    public void shoulCountCorrectAgreementPrice(Map.Entry<Date[], BigDecimal> date) {
        final int INDEX_OF_TO_DATE = 1;
        final int INDEX_OF_FROM_DATE = 0;


        TravelCalculatePremiumServiceUnderwriting underwriting
                = new TravelCalculatePremiumServiceUnderwriting();

        BigDecimal AgreementPrice
                = underwriting.calculatePremium(date.getKey()[INDEX_OF_FROM_DATE], date.getKey()[INDEX_OF_TO_DATE]);

        assertThat(AgreementPrice).isEqualTo(date.getValue());
    }

}
