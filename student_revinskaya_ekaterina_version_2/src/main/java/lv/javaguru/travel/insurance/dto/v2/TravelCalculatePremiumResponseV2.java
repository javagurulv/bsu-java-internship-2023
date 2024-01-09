package lv.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelCalculatePremiumResponseV2 extends CoreResponse {
    private String uuid;
    @JsonAlias("travel_ñost")
    private BigDecimal travelCost;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_from")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_to")
    private Date agreementDateTo;
    private String country;
    @JsonSerialize(using = BigDecimalSerializer.class)
    @JsonAlias("agreement_premium")
    private BigDecimal agreementPremium;
    private List<PersonResponse> persons;

    public TravelCalculatePremiumResponseV2(List<ValidationError> errors) {
        super(errors);
    }
}
