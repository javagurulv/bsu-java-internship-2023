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

    TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal cost) {

        return new TravelCalculatePremiumResponse(
                request.getPersonFirstName(),
                request.getPersonLastName(),
                request.getAgreementDateFrom(),
                request.getAgreementDateTo(),
                cost
        );
    }

}
