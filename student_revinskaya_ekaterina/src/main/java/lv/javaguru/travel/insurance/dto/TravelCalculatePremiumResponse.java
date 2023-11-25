package lv.javaguru.travel.insurance.dto;

import lv.javaguru.travel.insurance.core.util.BigDecimalSerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class TravelCalculatePremiumResponse  extends CoreResponse{

    private String personFirstName;
    private String personLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date agreementDateTo;
    private String country;
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;
    private List<TravelRisk> risks;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
