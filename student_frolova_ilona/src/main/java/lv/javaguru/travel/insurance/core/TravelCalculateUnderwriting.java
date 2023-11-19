package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public class TravelCalculateUnderwriting {

    private final DateTimeService dateTimeService;

    TravelCalculateUnderwriting() {
        dateTimeService = new DateTimeService();
    }

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeService.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
