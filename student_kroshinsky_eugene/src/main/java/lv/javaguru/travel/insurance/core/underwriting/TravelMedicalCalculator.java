package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelMedicalCalculator implements TravelRiskPremiumCalculator{
    @Autowired
    private DateTimeUtil dateTimeDifference;
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeDifference.calculateDateDifference(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
