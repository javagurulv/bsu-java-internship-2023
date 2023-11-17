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
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName,
                                          Date agreementDateFrom, Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;

        long diff = agreementDateTo.getTime() - agreementDateFrom.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        this.agreementPrice = new BigDecimal(days);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        TravelCalculatePremiumResponse anotherRequest = (TravelCalculatePremiumResponse) obj;
        return this.personFirstName.equals(anotherRequest.personFirstName) &&
                this.personLastName.equals(anotherRequest.personLastName) &&
                this.agreementDateFrom.equals(anotherRequest.agreementDateFrom) &&
                this.agreementDateTo.equals(anotherRequest.agreementDateTo) &&
                this.agreementPrice.equals(anotherRequest.agreementPrice);
    }

}
