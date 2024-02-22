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

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		final Stopwatch stopWatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		stopWatch.stop();
		requestTimeLogger.log(stopWatch);
		return response;
		//calculatePremiumService.calculatePremium(request);
	}

	private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		TravelCalculatePremiumCoreCommand command = buildCoreCommand(request);
		TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        return buildResponse(result);
	}

	private TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
		TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand();

		command.setAgreement(buildAgreement(request));

		return command;
	}
	private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult result) {
		if (result.hasErrors()) {
			return new TravelCalculatePremiumResponseV1(result.getErrors().stream().map(this::fromErrorDTO).collect(Collectors.toList()));
		}

		TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
		AgreementDTO agreement = result.getAgreement();
		PersonDTO person = agreement.getPersons().get(0);

		response.setAgreementPremium(result.getAgreement().getAgreementPremium().setScale(2, RoundingMode.HALF_UP));
		response.setRisks(person.getRisks().stream().map(this::fromRiskDTO).collect(Collectors.toList()));
		response.setCountry(result.getAgreement().getCountry());
		response.setPersonBirthDate(person.getPersonBirthDate());
		response.setAgreementDateTo(agreement.getAgreementDateTo());
		response.setAgreementDateFrom(agreement.getAgreementDateFrom());
		response.setAgreementPremium(agreement.getAgreementPremium());
		response.setPersonFirstName(person.getPersonFirstName());
		response.setPersonLastName(person.getPersonLastName());
		response.setMedicalRiskLimitLevel(agreement.getMedicalRiskLimitLevel());

		return response;
	}

	private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
		AgreementDTO agreement = new AgreementDTO();
		agreement.setPersons(List.of(buildPerson(request)));
		agreement.setAgreementDateFrom(request.getAgreementDateFrom());
		agreement.setAgreementDateTo(request.getAgreementDateTo());
		agreement.setCountry(request.getCountry());
		agreement.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
		agreement.setSelectedRisks(request.getSelectedRisks());
		return agreement;
	}

	private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
		PersonDTO person = new PersonDTO();
//		person.setRisks(request.getRisks().stream().map(this::toRiskDTO).collect(Collectors.toList()));
		person.setPersonFirstName(request.getPersonFirstName());
		person.setPersonLastName(request.getPersonLastName());
		person.setPersonBirthDate(request.getPersonBirthDate());
		return person;
	}
/*
	private RiskDTO toRiskDTO(TravelCalculatePremiumRisk risk) {
		return new RiskDTO(risk.getRiskIc(), risk.getPremium());
	}

 */
	private TravelCalculatePremiumRisk fromRiskDTO(RiskDTO risk) {
		return new TravelCalculatePremiumRisk(risk.getRiskIc(), risk.getPremium());
	}
	private ValidationError fromErrorDTO(ValidationErrorDTO error) {
		return new ValidationError(error.getErrorCode(), error.getDescription());
	}
}