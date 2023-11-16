package lv.javaguru.travel.insurance.rest;

import java.util.Date;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;

    public TravelCalculatePremiumResponse() {
    }

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName,
                                          Date agreementDateFrom, Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
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
                this.agreementDateTo.equals(anotherRequest.agreementDateTo);
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

}
