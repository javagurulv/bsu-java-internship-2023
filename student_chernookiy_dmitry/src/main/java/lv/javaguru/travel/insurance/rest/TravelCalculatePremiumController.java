package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.rest.loggers.RequestLogger;
import lv.javaguru.travel.insurance.rest.loggers.ResponseLogger;
import lv.javaguru.travel.insurance.rest.loggers.TimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumService calculatePremiumService;

	@Autowired private ResponseLogger responseLogger = new ResponseLogger();
	@Autowired private RequestLogger requestLogger = new RequestLogger();
	@Autowired private TimeLogger timeLogger = new TimeLogger();


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		requestLogger.log(request);
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponse response = processingRequest(request);
		timeLogger.log(stopwatch);
		responseLogger.log(response);
		return response;
	}

	private TravelCalculatePremiumResponse processingRequest(TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}


}
