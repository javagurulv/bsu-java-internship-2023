package lv.javaguru.travel.insurance.service.impl;

import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.service.UnderwritingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UnderwritingServiceImpl implements UnderwritingService {

    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        return BigDecimal.valueOf(request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime());
    }
}
