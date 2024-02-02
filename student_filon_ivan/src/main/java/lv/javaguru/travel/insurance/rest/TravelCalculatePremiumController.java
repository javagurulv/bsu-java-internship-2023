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
	private TravelCalculatePremiumRequestProcessingTimeLogger timeLogger;
	private final StopWatch stopWatch = new StopWatch();


	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")

	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		stopWatch.start();
		TravelCalculatePremiumResponse response = processRequest(request);
		timeLogger.toLog(stopWatch);
		return response;
	}

	private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request){
		requestLogger.toLog(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremiumResponse(request);
		responseLogger.toLog(response);
		return response;
	}

}