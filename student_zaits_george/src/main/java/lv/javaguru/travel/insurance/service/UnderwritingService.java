package lv.javaguru.travel.insurance.service;

import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface UnderwritingService {

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
