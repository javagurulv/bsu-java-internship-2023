package lv.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lv.javaguru.travel.insurance.core.ValidationError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateTo;
    private BigDecimal agreementPrice;
    private List<InsurancePremiumRisk> risks;
    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
    /*
    public void initAgreementPrice() {
        agreementPrice = BigDecimal.valueOf(agreementDateTo.getDay() - agreementDateFrom.getDay());
    }
     */
}
