package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.*;
import lv.javaguru.travel.insurance.core.ValidationError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
    public void initAgreementPrice() {
        agreementPrice = BigDecimal.valueOf(agreementDateTo.getDay() - agreementDateFrom.getDay());
    }
}
