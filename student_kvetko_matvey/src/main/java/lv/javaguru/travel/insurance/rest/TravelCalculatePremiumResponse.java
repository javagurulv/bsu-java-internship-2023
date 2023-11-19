package lv.javaguru.travel.insurance.rest;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName,
                                          String personLastName,
                                          Date agreementDateFrom,
                                          Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
        long diff = agreementDateTo.getTime() - agreementDateFrom.getTime();
        this.agreementPrice = BigDecimal.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
    }
}
