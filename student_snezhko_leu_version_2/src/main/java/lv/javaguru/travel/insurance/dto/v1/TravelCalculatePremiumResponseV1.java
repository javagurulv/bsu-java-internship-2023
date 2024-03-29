package lv.javaguru.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String personIc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    private List<TravelCalculatePremiumRisk> risks;
    private String country;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date personBirthDate;

    private String medicalRiskLimitLevel;
    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }
    /*
    public void initAgreementPrice() {
        agreementPrice = BigDecimal.valueOf(agreementDateTo.getDay() - agreementDateFrom.getDay());
    }
     */
}
