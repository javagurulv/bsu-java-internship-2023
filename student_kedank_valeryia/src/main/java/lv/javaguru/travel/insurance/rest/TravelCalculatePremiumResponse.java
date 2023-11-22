package lv.javaguru.travel.insurance.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName,
                                          Date agreementDateFrom, Date agreementDateTo){
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;

        long temp = agreementDateTo.getTime() - agreementDateFrom.getTime();
        long days = TimeUnit.DAYS.convert(temp, TimeUnit.MILLISECONDS);
        this.agreementPrice = BigDecimal.valueOf(days);
    }

}
