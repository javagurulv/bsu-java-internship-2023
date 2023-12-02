package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired private TravelCalculatePremiumExecutionTimeLogger executionTimeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		StopWatch stopWatch = new StopWatch();
		requestLogger.log(request);
		stopWatch.start();
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		stopWatch.stop();
		responseLogger.log(response);
		executionTimeLogger.log(stopWatch.getLastTaskTimeMillis());
		return response;
	}

}