package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelRisk;

import java.math.BigDecimal;
import java.util.List;

public interface TravelPremiumUnderwriting {
    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
}
