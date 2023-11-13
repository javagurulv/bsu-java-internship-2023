package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
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

        this.agreementPrice = getDifferenceInDays(this.agreementDateFrom, this.agreementDateTo);
    }

    private BigDecimal getDifferenceInDays(Date date1, Date date2) {
        BigDecimal difference = new BigDecimal(date2.getTime() - date1.getTime());
        difference = difference.divide(BigDecimal.valueOf(1000.0), MathContext.DECIMAL128);
        difference = difference.divide(BigDecimal.valueOf(86400.0), MathContext.DECIMAL128);

        return difference;
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
