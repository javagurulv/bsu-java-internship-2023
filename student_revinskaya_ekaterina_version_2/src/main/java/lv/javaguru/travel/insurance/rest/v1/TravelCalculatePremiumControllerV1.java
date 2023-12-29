package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.rest.TravelCalculateRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public
class TravelCalculatePremiumControllerV1 {
    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLoggerV1 loggerForRequest;
    @Autowired
    private TravelCalculatePremiumResponseLoggerV1 loggerForResponse;
    @Autowired
    private TravelCalculateRequestExecutionTimeLogger requestExecutionTimeLogger;
    @Autowired
    private DtoV1Converter dtoV1Converter;
    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
        loggerForRequest.log(request);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreCommand command = dtoV1Converter.commandFromRequestV1(request);
        TravelCalculatePremiumCoreResult result = calculatePremiumService.calculatePremium(command);
        TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponseV1fromCoreResult(result);
        stopwatch.stop();
        loggerForResponse.log(response);
        requestExecutionTimeLogger.log(stopwatch.elapsed().toMillis());
        return response;
    }

}