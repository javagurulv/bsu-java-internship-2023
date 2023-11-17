package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse premiumResponse = new TravelCalculatePremiumResponse();
        premiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        premiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        premiumResponse.setPersonFirstName(request.getPersonFirstName());
        premiumResponse.setPersonLastName(request.getPersonLastName());

        BigDecimal daysDiff = new BigDecimal
                                            (DataTimeService.getDaysBetween(request.getAgreementDateFrom(),
                                                                            request.getAgreementDateTo()));
        premiumResponse.setAgreementPrice(daysDiff);
        return premiumResponse;
    }

}
