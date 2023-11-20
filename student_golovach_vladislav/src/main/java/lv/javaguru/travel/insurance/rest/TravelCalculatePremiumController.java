package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@GetMapping("/hello")
	public String hello() {
		return "Hello from your Java application!";
	}
	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}

}