package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class RiskDto {
    private String riskIc;

    private BigDecimal premium;
}
