package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal  agreementPrice = new BigDecimal(TimeUnit.DAYS.convert(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime(), TimeUnit.MILLISECONDS));
        return new TravelCalculatePremiumResponse(request.getPersonFirstName(),
                                                    request.getPersonLastName(),
                                                    request.getAgreementDateFrom(),
                                                    request.getAgreementDateTo(),
                                                    agreementPrice);

    }

}
