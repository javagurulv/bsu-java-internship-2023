package lv.javaguru.travel.insurance.core.services;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwriting;
//import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwritingImpl;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRiskFactory;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestExecutionTimeLogger;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator validator;
    @Autowired private TravelCalculatePremiumRequestLogger requestLogger;
    @Autowired private TravelCalculatePremiumResponseLogger responseLogger;
    @Autowired private TravelCalculatePremiumRequestExecutionTimeLogger requestTimeLogger;
    @Autowired private TravelUnderwriting underwriting;
    @Autowired private TravelCalculatePremiumRiskFactory riskFactory;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
//        validator = new TravelCalculatePremiumRequestValidator();
        final Stopwatch stopWatch = Stopwatch.createStarted();
        List<ValidationError> errors = validator.validate(request);
        requestLogger.log(request);
        TravelCalculatePremiumResponse response = !errors.isEmpty()
                ? buildResponse(errors)
                : buildResponse(request);
        stopWatch.stop();
        requestTimeLogger.log(stopWatch);
        responseLogger.log(response);
        return response;
    }
    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        //response.setRisks();
        //response.setRisks(request.getSelectedisks());
        //response.initAgreementPrice();

        //here could use underwriting.calculatePremium, which just adds all risks from response,
        // not recalculate them once again
        response.setRisks(riskFactory.buildRisksList(request));//underwriting.getRiskCalculators(), request));
        response.setAgreementPremium(underwriting.calculatePremium(request));//response));
        response.setCountry(request.getCountry());
        response.setPersonBirthDate(request.getPersonBirthDate());
        return response;
    }
    public TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }
}
