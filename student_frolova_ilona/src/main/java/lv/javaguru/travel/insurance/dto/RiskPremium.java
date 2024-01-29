package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.serializers.MoneySerializer;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class RiskPremium {

    private String riskIc;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal premium;

}
