package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.logger.TimeLogger;
import lv.javaguru.travel.insurance.core.logger.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.core.logger.TravelCalculatePremiumResponseLogger;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConfig.insuranceTravel)
public class TravelCalculatePremiumController {

	@Autowired
	private TravelCalculatePremiumService calculatePremiumService;

	@Autowired
	TravelCalculatePremiumRequestLogger requestLogger;

	@Autowired
	TravelCalculatePremiumResponseLogger responseLogger;

	@Autowired
	TimeLogger timeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		requestLogger.logRequest(request);
		Stopwatch time = Stopwatch.createStarted();
		var response = calculatePremiumService.calculatePremium(request);
		timeLogger.logElapsedTime(time);
		responseLogger.logResponse(response);
		return response;
	}

}