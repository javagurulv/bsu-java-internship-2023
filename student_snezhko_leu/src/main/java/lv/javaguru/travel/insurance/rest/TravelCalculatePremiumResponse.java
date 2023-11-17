package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import lombok.*;

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

    public void initAgreementPrice() {
        agreementPrice = BigDecimal.valueOf(agreementDateTo.getDay() - agreementDateFrom.getDay());
    }
}
