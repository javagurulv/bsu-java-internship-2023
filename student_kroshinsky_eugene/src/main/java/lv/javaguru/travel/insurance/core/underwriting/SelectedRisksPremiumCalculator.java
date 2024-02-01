package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;

import java.util.List;

public interface SelectedRisksPremiumCalculator {
    List<TravelRisk> calculateTravelRisksList(TravelCalculatePremiumRequest request);
}
