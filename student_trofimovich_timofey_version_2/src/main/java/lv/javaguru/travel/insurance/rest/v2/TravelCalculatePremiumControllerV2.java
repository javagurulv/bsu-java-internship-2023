package lv.javaguru.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v2.DTOV2Converter;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelCalculatePremiumControllerV2 {
    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLoggerV2 requestLogger;
    @Autowired
    private TravelCalculatePremiumResponseLoggerV2 responseLogger;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
	@Autowired
    private DTOV2Converter converter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponseV2 response = processRequest(request);
        timeLogger.log(stopwatch);
        return response;
    }

    public TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
        requestLogger.log(request);
        TravelCalculatePremiumCoreCommand coreCommand = converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV2 response = converter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }



}