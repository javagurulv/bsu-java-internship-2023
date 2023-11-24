package lv.javaguru.travel.insurance.validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;

    private BigDecimal agreementPremium;
    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;

    private String country;
    private List<RiskPremium> risks;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;
}
