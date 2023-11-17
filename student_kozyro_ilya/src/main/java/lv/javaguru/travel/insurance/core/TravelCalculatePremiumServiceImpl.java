package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    DateServiceImpl dateService;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        return TravelCalculatePremiumResponse.builder()
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementDateTo(request.getAgreementDateTo())
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementPrice(dateService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()))
                .build();
    }

}
