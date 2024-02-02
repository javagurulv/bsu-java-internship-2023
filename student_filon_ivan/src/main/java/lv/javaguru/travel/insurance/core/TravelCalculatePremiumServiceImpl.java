package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private TravelPremiumUnderwriting underwriting;
    @Autowired private TravelCalculatePremiumRequestValidator validator;

    @Override
    public TravelCalculatePremiumResponse calculatePremiumResponse(TravelCalculatePremiumRequest request) {
        List<ValidationError> list = validator.validate(request);
        return list.isEmpty()
                ? buildResponse(request, underwriting.calculatePremium(request))
                : buildResponse(list);
    }

    public TravelCalculatePremiumResponse buildResponse(List<ValidationError> list){
        return new TravelCalculatePremiumResponse(list);
    }

    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal premium){
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementPrice(premium);

        return response;
    }
}
