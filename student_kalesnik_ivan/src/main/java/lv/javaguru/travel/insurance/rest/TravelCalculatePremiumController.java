package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumResponseLogger;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private TravelCalculatePremiumRequestLogger loggerForRequest;
	@Autowired private TravelCalculatePremiumResponseLogger loggerForResponse;
	@Autowired private TravelCalculatePremiumRequestExecutionTimeLogger requestExecutionTimeLogger;
	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		loggerForRequest.log(request);
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		//stopwatch.stop();
		loggerForResponse.log(response);
		requestExecutionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

}