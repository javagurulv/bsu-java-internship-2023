package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
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

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		requestLogger.logRequest(request);
		var response = calculatePremiumService.calculatePremium(request);
		responseLogger.logResponse(response);
		return response;
	}

}