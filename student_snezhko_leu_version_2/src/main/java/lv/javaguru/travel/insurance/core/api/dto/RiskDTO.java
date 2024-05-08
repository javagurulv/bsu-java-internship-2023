package lv.javaguru.travel.insurance.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.util.BigDecimalSerializer;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiskDTO {

    private String riskIc;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal premium;

}
