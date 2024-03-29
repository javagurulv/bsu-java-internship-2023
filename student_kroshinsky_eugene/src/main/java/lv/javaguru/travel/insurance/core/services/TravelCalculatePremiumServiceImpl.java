package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.SelectedRisksPremiumCalculator;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.core.validations.PremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private SelectedRisksPremiumCalculator electedRisksCalculatorImpl;
    @Autowired private PremiumRequestValidator requestValidator;
    @Autowired private TravelCalculatePremiumRequestLogger logger;
    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty() ? buildResponse(request) : buildResponse(errors);
    }
    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors){
        return new TravelCalculatePremiumResponse(errors);
    }
    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request){
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPremium(premiumUnderwriting.calculatePremium(request));
        response.setRisks(electedRisksCalculatorImpl.calculateTravelRisksList(request));
        response.setCountry(request.getCountry());
        response.setDateOfBirth(request.getDateOfBirth());
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return response;
    }
}
