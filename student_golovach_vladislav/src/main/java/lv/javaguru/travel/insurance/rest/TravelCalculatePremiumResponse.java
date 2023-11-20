package lv.javaguru.travel.insurance.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse{
    BigDecimal agreementPrice;
    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
