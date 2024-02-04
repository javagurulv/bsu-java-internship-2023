package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lv.javaguru.travel.insurance.core.serializers.MoneySerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal agreementPremium;

    private List<RiskPremium> risks;

    private String country;

    private String medicalRiskLimitLevel;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
}
