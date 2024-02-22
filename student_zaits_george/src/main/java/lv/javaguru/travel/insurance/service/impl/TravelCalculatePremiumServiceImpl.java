package lv.javaguru.travel.insurance.service.impl;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.service.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.service.UnderwritingService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator validator;
    private final UnderwritingService underwritingService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        validator.validateTravelCalculatePremiumRequest(request);

        var agreementPrice = underwritingService.calculateAgreementPrice(request);

        return TravelCalculatePremiumResponse.builder()
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementDateTo(request.getAgreementDateTo())
                .agreementPrice(agreementPrice)
                .build();
    }

}
