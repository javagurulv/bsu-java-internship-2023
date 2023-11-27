package lv.javaguru.travel.insurance.validation.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.validation.CoreResponse;
import lv.javaguru.travel.insurance.validation.RiskPremium;
import lv.javaguru.travel.insurance.validation.ValidationError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;

    private String country;

    private String medicalRiskLimitLevel;
    private BigDecimal agreementPremium;
    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }


    private List<RiskPremium> risks;

}
