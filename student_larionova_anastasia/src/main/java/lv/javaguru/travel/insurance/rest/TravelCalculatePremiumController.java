package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.logger.TravelCalculatePremiumResponseLoggerImpl;
import lv.javaguru.travel.insurance.logger.TimingTravelCalculatePremiumRequestLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	@Autowired private TimingTravelCalculatePremiumRequestLogger requestLogger;
	@Autowired private TravelCalculatePremiumResponseLoggerImpl responseLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		requestLogger.logRequest(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		responseLogger.logResponse(response);
		return calculatePremiumService.calculatePremium(request);
	}

}