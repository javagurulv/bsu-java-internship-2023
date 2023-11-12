package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse() {}

    public TravelCalculatePremiumResponse(String personFirstName,
                                         String personLastName,
                                         Date agreementDateFrom,
                                         Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;

        this.agreementPrice = new BigDecimal(
                BigInteger.valueOf(getDifferenceInDays(this.agreementDateFrom, this.agreementDateTo))
        );
    }

    private long getDifferenceInDays(Date date1, Date date2) {
        Instant instant1 = date1.toInstant();
        Instant instant2 = date2.toInstant();

        Duration duration = Duration.between(instant1, instant2);
        return duration.toDays();
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    } // ?

    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
    } // ?

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
