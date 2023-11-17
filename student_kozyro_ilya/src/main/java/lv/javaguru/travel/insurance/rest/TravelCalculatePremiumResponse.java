package lv.javaguru.travel.insurance.rest;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;

    private String personLastName;

    private Date agreementDateFrom;

    private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse() {

    }

}
