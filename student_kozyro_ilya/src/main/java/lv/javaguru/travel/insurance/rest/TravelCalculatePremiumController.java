package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.logger.MLogger;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lv.javaguru.travel.insurance.rest.RestConfig.insuranceTravel;

@RestController
@RequestMapping(RestConfig.insuranceTravel)
public class TravelCalculatePremiumController {

	@Autowired
	private TravelCalculatePremiumService calculatePremiumService;

	@Autowired
	MLogger mLogger;
	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		mLogger.logTravelPremiumRequest(request);
		return calculatePremiumService.calculatePremium(request);
	}

}