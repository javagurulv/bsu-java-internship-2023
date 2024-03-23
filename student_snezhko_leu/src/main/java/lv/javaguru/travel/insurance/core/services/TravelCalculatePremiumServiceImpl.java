package lv.javaguru.travel.insurance.core.services;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwriting;
//import lv.javaguru.travel.insurance.core.underwriting.TravelUnderwritingImpl;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
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
    public TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request) {
//        validator = new TravelCalculatePremiumRequestValidator();
        final Stopwatch stopWatch = Stopwatch.createStarted();
        List<ValidationError> errors = validator.validate(request);
        requestLogger.log(request);
        TravelCalculatePremiumResponseV1 response = !errors.isEmpty()
                ? buildResponse(errors)
                : buildResponse(request);
        stopWatch.stop();
        requestTimeLogger.log(stopWatch);
        responseLogger.log(response);
        return response;
    }
    public TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumRequestV1 request) {
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
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
        response.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        response.setPersonIc(request.getPersonIc());
        return response;
    }
    public TravelCalculatePremiumResponseV1 buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponseV1(errors);
    }
}
