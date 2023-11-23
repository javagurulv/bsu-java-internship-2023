package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.request.processor.TravelCalculatePremiumRequestProcessor;
import lv.javaguru.travel.insurance.core.services.DateServiceImpl;
import lv.javaguru.travel.insurance.core.validator.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    DateServiceImpl dateService;
    TravelCalculatePremiumRequestValidator validator;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        return new TravelCalculatePremiumRequestProcessor(dateService, validator).buildResponse(request);
    }

}
