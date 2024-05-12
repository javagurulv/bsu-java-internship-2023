package lv.javaguru.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.v2.ConverterV2DTO;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.core.services.calculate.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelInsuranceRestControllerV2 {
    @Autowired
    private TravelCalculatePremiumService service;

    @Autowired
    private ConverterV2DTO converterV2;

    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
        final Stopwatch stopwatch = Stopwatch.createStarted();

        TravelCalculatePremiumCoreCommand command = converterV2.buildCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        TravelCalculatePremiumResponseV2 response = converterV2.buildResponse(result);
        stopwatch.stop();

        timeLogger.log(stopwatch);

        return response;//result;
    }
}
