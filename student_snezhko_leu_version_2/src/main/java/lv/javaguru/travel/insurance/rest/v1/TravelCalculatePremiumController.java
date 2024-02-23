package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.rest.v1.loggers.TravelCalculatePremiumRequestExecutionTimeLogger;
import lv.javaguru.travel.insurance.rest.v1.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.rest.v1.loggers.TravelCalculatePremiumResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumController {
	@Autowired
	private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired
	private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired
	private TravelCalculatePremiumRequestExecutionTimeLogger requestTimeLogger;
	@Autowired
	private TravelCalculatePremiumService service;

	@Autowired
	private DtoV1Converter converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		final Stopwatch stopWatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = converter.processRequest(request, service);
		stopWatch.stop();
		requestTimeLogger.log(stopWatch);
		return response;
		//calculatePremiumService.calculatePremium(request);
	}


}