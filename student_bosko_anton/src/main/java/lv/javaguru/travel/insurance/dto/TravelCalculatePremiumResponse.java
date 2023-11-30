package lv.javaguru.travel.insurance.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.DateTimeService;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(TravelCalculatePremiumRequest request) {
        this.setPersonFirstName(request.getPersonFirstName());
        this.setPersonLastName(request.getPersonLastName());
        this.setAgreementDateFrom(request.getAgreementDateFrom());
        this.setAgreementDateTo(request.getAgreementDateTo());
        DateTimeService daysBetween = new DateTimeService();
        long difference = daysBetween.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        this.setAgreementPrice(new BigDecimal(difference));
    }

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        else if (obj == this)
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
