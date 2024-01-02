package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumController {
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		timeLogger.log(stopwatch);
		return response;
	}
	public TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV1 response = buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

	private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
		return coreResult.hasErrors() ? buildResponseWithErrors(coreResult) : buildSuccessfulResponse(coreResult);
	}

	private TravelCalculatePremiumResponseV1 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
		TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
		AgreementDTO agreement = coreResult.getAgreement();
		response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
		response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
		response.setAgreementDateFrom(agreement.getAgreementDateFrom());
		response.setAgreementDateTo(agreement.getAgreementDateTo());
		response.setDateOfBirth(agreement.getPersons().get(0).getPersonBirthDate());
		response.setCountry(agreement.getCountry());
		response.setAgreementPremium(agreement.getAgreementPremium());
		response.setMedicalRiskLimitLevel(agreement.getMedicalRiskLimitLevel());
		List<RiskPremium> risks = agreement.getPersons().get(0).getSelectedRisks().stream()
				.map(risk -> new RiskPremium(risk.getRiskIc(), risk.getPremium()))
				.toList();
		response.setRisks(risks);
		return response;
	}

	private TravelCalculatePremiumResponseV1 buildResponseWithErrors(TravelCalculatePremiumCoreResult coreResult) {
		List<ValidationError> errors = transformValidationErrors(coreResult.getErrors());
		return new TravelCalculatePremiumResponseV1(errors);
	}

	private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> errors) {
		return errors.stream().map(error -> new ValidationError(error.getErrorCode(), error.getDescription())).toList();
	}

	private TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 requestV1) {
		AgreementDTO agreement = buildAgreement(requestV1);
		return new TravelCalculatePremiumCoreCommand(agreement);
	}

	private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 requestV1) {
		AgreementDTO agreement = new AgreementDTO();
		agreement.setAgreementDateFrom(requestV1.getAgreementDateFrom());
		agreement.setAgreementDateTo(requestV1.getAgreementDateTo());
		agreement.setMedicalRiskLimitLevel(requestV1.getMedicalRiskLimitLevel());
		agreement.setCountry(requestV1.getCountry());
		agreement.setSelectedRisks(requestV1.getSelectedRisks());
		PersonDTO person = buildPerson(requestV1);
		agreement.setPersons(List.of(person));
		return agreement;
	}

	private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 requestV1) {
		PersonDTO person = new PersonDTO();
		person.setPersonFirstName(requestV1.getPersonFirstName());
		person.setPersonBirthDate(requestV1.getDateOfBirth());
		person.setPersonLastName(requestV1.getPersonLastName());
		return person;
	}

}