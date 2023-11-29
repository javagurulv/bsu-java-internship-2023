package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse() {}

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

    public BigDecimal getAgreementPrice() { return agreementPrice; }

    public void setAgreementPrice(BigDecimal agreementPrice) { this.agreementPrice = agreementPrice; }
}
