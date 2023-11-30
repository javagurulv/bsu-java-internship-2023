package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public TravelCalculatePremiumResponse(TravelCalculatePremiumRequest request)
    {
        this.setPersonFirstName(request.getPersonFirstName());
        this.setPersonLastName(request.getPersonLastName());
        this.setAgreementDateFrom(request.getAgreementDateFrom());
        this.setAgreementDateTo(request.getAgreementDateTo());
        this.setAgreementPrice(BigDecimal.valueOf(TimeUnit.DAYS.convert(agreementDateTo.getTime() - agreementDateFrom.getTime(),
                TimeUnit.MILLISECONDS)));
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        else if(obj == this)
            return true;
        else {
            TravelCalculatePremiumResponse temp = (TravelCalculatePremiumResponse) obj;
            return Objects.equals(personFirstName, temp.getPersonFirstName()) &&
                    Objects.equals(personLastName, temp.getPersonLastName()) &&
                    agreementDateFrom == temp.getAgreementDateFrom() &&
                    agreementDateTo == temp.getAgreementDateTo() &&
                    Objects.equals(agreementPrice, temp.getAgreementPrice());
        }
    }
}
