package lv.javaguru.travel.insurance.controller;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.model.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.service.TravelCalculatePremiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
@RequiredArgsConstructor
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}

}