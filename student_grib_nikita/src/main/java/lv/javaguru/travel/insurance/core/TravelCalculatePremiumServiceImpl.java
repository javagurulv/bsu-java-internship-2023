package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }
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
