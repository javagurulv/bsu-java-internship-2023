package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
                                          Date agreementDateTo, BigDecimal agreementPrice) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
        this.agreementPrice = agreementPrice;
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

        return this.getAgreementDateFrom().equals(response.getAgreementDateFrom()) && this.getAgreementDateTo().equals(response.getAgreementDateTo()) && this.getPersonFirstName().equals(response.getPersonFirstName()) && this.getPersonLastName().equals(response.getPersonLastName());

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
        this.agreementPrice = agreementPrice;
    }
}
