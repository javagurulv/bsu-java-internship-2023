package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    TravelCalculatePremiumRequestValidator validator;

    @Autowired
    TravelPremiumUnderwriting travelPremiumUnderwriting;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        var errors = validator.validate(request);

        return errors.isEmpty() ?
                buildResponse(request, travelPremiumUnderwriting.calculatePremium(request)) :
                buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal agreementPrice) {
        return TravelCalculatePremiumResponse.builder()
                .personFirstName(request.getPersonFirstName())
                .personLastName(request.getPersonLastName())
                .agreementDateTo(request.getAgreementDateTo())
                .agreementDateFrom(request.getAgreementDateFrom())
                .agreementPrice(agreementPrice)
                .build();
    }

}
