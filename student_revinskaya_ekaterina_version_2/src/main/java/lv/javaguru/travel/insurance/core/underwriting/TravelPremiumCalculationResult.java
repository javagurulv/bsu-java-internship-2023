package lv.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TravelPremiumCalculationResult {
    private BigDecimal totalPremium;
    private List<RiskDTO> risks;

}
