package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.*;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired
    private TravelPremiumUnderwriting calculateUnderwriting;

    @Override
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {

        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return buildErrorResponse(errors);
        }
        return buildSuccessResponse(request);
    }

    private TravelCalculatePremiumResponseV1 buildErrorResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private TravelCalculatePremiumResponseV1 buildSuccessResponse(TravelCalculatePremiumRequestV1 request) {
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        TravelPremiumCalculatorResult calculatorResult = calculateUnderwriting.calculatePremium(request);
        response.setAgreementPremium(calculatorResult.getTotalPremium());
        response.setRisks(calculatorResult.getTravelRisks());
        response.setCountry(request.getCountry());
        response.setBirthday(request.getBirthday());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return response;
    }
}