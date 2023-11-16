package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse() {
    }

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
        this.agreementPrice = new BigDecimal(agreementPrice.toString());
    }

}
