package lv.javaguru.travel.insurance.rest;

import lombok.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public static BigDecimal getDifferenceInDays(Date date1, Date date2) {
        BigDecimal difference = new BigDecimal(date2.getTime() - date1.getTime());
        difference = difference.divide(BigDecimal.valueOf(1000.0), MathContext.DECIMAL128);
        difference = difference.divide(BigDecimal.valueOf(86400.0), MathContext.DECIMAL128);

        return difference;
    }
}
