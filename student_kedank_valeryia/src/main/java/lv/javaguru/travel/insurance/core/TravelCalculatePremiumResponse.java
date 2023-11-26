package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.CoreResponse;
import lv.javaguru.travel.insurance.validators.ValidationError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;

    //разница между двумя датами
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName,
                                          Date agreementDateFrom, Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;

        //разница между двумя датами
        long temp = agreementDateTo.getTime() - agreementDateFrom.getTime();
        long days = TimeUnit.DAYS.convert(temp, TimeUnit.MILLISECONDS);
        this.agreementPrice = BigDecimal.valueOf(days);
    }

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
