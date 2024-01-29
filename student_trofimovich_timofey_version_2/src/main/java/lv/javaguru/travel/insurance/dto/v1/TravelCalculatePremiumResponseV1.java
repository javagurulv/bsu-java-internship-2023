package lv.javaguru.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personUUID;

    private String personFirstName;

    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    private List<RiskPremium> risks;

    private String country;

    private String medicalRiskLimitLevel;

    private String uuid;
    private BigDecimal travelCost;

    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }


}
