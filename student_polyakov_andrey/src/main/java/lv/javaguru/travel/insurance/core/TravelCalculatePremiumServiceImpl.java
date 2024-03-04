package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Autowired
    private TravelUnderwritingProcess underwritingProcess;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        if (errors.isEmpty()) {
            return createResponse(request, underwritingProcess.calculatePremium(request));
        }
        else {
            return createResponse(errors);
        }
    }

    public TravelCalculatePremiumResponse createResponse(TravelCalculatePremiumRequest request, BigDecimal agreementPrice) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementPrice(agreementPrice);
        return response;
    }

    public TravelCalculatePremiumResponse createResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

}
