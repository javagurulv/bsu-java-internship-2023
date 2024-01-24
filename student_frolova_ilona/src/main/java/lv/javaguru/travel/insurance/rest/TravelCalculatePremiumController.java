package lv.javaguru.travel.insurance.rest;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {
	private final TravelCalculatePremiumService calculatePremiumService;
	private final TravelCalculatePremiumRequestLogger requestLogger;
	private final TravelCalculatePremiumResponseLogger responseLogger;
	private final TravelCalculatePremiumExecutionTimeLogger executionTimeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		TravelCalculatePremiumResponse response = processRequest(request);
		stopWatch.stop();

		executionTimeLogger.log(stopWatch.getLastTaskTimeMillis());
		return response;
	}

	private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request) {
		requestLogger.log(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		responseLogger.log(response);
		return response;
	}

}