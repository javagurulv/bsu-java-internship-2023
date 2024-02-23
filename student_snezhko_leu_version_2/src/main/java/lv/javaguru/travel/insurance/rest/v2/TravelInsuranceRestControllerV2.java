package lv.javaguru.travel.insurance.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
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
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumCoreResult calculatePremium(@RequestBody TravelCalculatePremiumCoreCommand command) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        stopwatch.stop();

        timeLogger.log(stopwatch);

        return result;
    }
}
