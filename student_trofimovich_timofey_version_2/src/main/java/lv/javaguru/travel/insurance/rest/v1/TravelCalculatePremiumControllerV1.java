package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.DTOV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumControllerV1 {
    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLoggerV1 requestLogger;
    @Autowired
    private TravelCalculatePremiumResponseLoggerV1 responseLogger;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
	@Autowired
    private DTOV1Converter converter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponseV1 response = processRequest(request);
        timeLogger.log(stopwatch);
        return response;
    }

    public TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
        requestLogger.log(request);
        TravelCalculatePremiumCoreCommand coreCommand = converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV1 response = converter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }



}