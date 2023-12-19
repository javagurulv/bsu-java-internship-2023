package lv.javaguru.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.rest.TravelCalculateRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
class TravelCalculatePremiumControllerV2 {
    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLoggerV2 loggerForRequest;
    @Autowired
    private TravelCalculatePremiumResponseLoggerV2 loggerForResponse;
    @Autowired
    private TravelCalculateRequestExecutionTimeLogger requestExecutionTimeLogger;
    @Autowired
    private DtoV2Converter dtoV2Converter;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
        loggerForRequest.log(request);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreCommand command = dtoV2Converter.commandFromRequestV2(request);
        TravelCalculatePremiumCoreResult result = calculatePremiumService.calculatePremium(command);
        TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildResponseV2fromCoreResult(result);
        stopwatch.stop();
        loggerForResponse.log(response);
        requestExecutionTimeLogger.log(stopwatch.elapsed().toMillis());
        return response;
    }

}