package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;


    public TravelCalculatePremiumResponse() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TravelCalculatePremiumResponse response = (TravelCalculatePremiumResponse) o;

        return this.getAgreementDateFrom().equals(response.getAgreementDateFrom()) && this.getAgreementDateTo().equals(response.getAgreementDateTo()) && this.getPersonFirstName().equals(response.getPersonFirstName()) && this.getPersonLastName().equals(response.getPersonLastName()) && this.getAgreementPrice().equals(response.getAgreementPrice());

    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Date getAgreementDateFrom() {
        return agreementDateFrom;
    }

    public void setAgreementDateFrom(Date agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
    }

    public Date getAgreementDateTo() {
        return agreementDateTo;
    }

    public void setAgreementDateTo(Date agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = new BigDecimal(agreementPrice.toString()) ;
    }
}
