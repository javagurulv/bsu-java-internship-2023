package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLogger requestLogger;
    @Autowired
    private TravelCalculatePremiumResponseLogger responseLogger;
    @Autowired
    private TravelCalculatePremiumRequestProcessingTimeLogger responseTimeLogger;

    private TravelCalculatePremiumResponse handleRequest(TravelCalculatePremiumRequest request) {
        requestLogger.log(request);
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        responseLogger.log(response);
        return response;
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        Stopwatch stopWatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponse response = handleRequest(request);
        responseTimeLogger.log(stopWatch);
        return response;
    }

}