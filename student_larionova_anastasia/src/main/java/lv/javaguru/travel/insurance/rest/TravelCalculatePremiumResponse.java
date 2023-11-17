package lv.javaguru.travel.insurance.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;
    public void calculateAgreementPrice() {
        long daysDifference = ChronoUnit.DAYS.between(agreementDateFrom.toInstant(), agreementDateTo.toInstant());
        this.agreementPrice = BigDecimal.valueOf(daysDifference);
    }

}
