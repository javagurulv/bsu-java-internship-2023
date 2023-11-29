package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class TravelPremiumCalculatorResult {
    private BigDecimal totalPremium;
    private List<TravelRisk> travelRisks;

    public TravelPremiumCalculatorResult(List<TravelRisk> travelRisks, BigDecimal totalPremium) {
    }
}
