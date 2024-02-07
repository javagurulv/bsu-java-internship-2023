package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SelectedRisksPremiumCalculatorImpl implements SelectedRisksPremiumCalculator{
    @Autowired
    private List<TravelRiskPremiumCalculator> calculators;
    @Override
    public List<TravelRisk> calculateTravelRisksList(TravelCalculatePremiumRequest request) {
        List<String> selectedRisks = request.getSelectedRisks();
        return calculators.stream()
                .filter(c->selectedRisks.contains(c.getRiskIc()))
                .map(m -> new TravelRisk(m.getRiskIc(), m.calculatePremium(request)))
                .collect(Collectors.toList());
    }
}
