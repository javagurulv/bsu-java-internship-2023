package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.loggers.TravelCalculatePremiumResponseLogger;
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
	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		loggerForRequest.log(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		loggerForResponse.log(response);
		return response;
	}

}