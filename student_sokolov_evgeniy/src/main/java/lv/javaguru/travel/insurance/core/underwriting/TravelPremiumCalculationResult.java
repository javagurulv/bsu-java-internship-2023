package lv.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.javaguru.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskPremium> riskPremiums;

}
