package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.util.BigDecimalSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;
    private String country;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String medicalRiskLimitLevel;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;
    private List<TravelRisk> risks;

    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }

}
