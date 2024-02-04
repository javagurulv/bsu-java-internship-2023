package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.underwriting.TravelCalculateUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Autowired
    private TravelCalculateUnderwriting underwritingCalculator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty() ? buildResponse(request, underwritingCalculator.calculatePremium(request)) : buildResponse(errors);
    }

    TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, TravelPremiumCalculationResult premium) {

        return new TravelCalculatePremiumResponse(
                request.getPersonFirstName(),
                request.getPersonLastName(),
                request.getPersonBirthDate(),
                request.getAgreementDateFrom(),
                request.getAgreementDateTo(),
                premium.getTotalPremium(),
                premium.getRiskPremiums(),
                request.getCountry(),
                request.getMedicalRiskLimitLevel()
        );
    }

}
