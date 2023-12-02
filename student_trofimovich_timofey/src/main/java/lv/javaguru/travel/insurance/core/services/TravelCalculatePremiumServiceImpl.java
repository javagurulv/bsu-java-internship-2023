package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty()
                ? buildResponse(request, premiumUnderwriting.calculatePremium(request))
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, TravelPremiumCalculationResult calculationResult) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setDateOfBirth(request.getDateOfBirth());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPremium(calculationResult.getTotalPremium());
        response.setRisks(calculationResult.getRiskPremiums());
        response.setCountry(request.getCountry());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return response;
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

}
