package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelRisk;

import java.util.List;

public interface SelectedRisksPremiumCalculator {
    List<TravelRisk> calculateTravelRisksList(TravelCalculatePremiumRequestV1 request);
}
