package lv.javaguru.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v2.DtoV2CONVERTER;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelCalculatePremiumServiceV2 {
    @Autowired
    private TravelCalculatePremiumRequestLoggerV2 requestLogger;
    @Autowired private TravelCalculatePremiumResponseLoggerV2 responseLogger;
    @Autowired private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
    @Autowired private TravelCalculatePremiumService calculatePremiumService;
    @Autowired private DtoV2CONVERTER dtoV2Converter;

    public TravelCalculatePremiumResponseV2 calculatePremium(TravelCalculatePremiumRequestV2 request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        requestLogger.log(request);
        TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildResponse(coreResult);
        responseLogger.log(response);
        executionTimeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
