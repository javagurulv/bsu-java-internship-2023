package lv.javaguru.travel.insurance.v1;

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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insurance/travel/api/v1")
public class TravelCalculatePremiumController {

	@Autowired private TravelCalculatePremiumRequestLogger requestLogger;
	@Autowired private TravelCalculatePremiumResponseLogger responseLogger;
	@Autowired private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV1 response = buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

	private TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
		AgreementDTO agreement = buildAgreement(request);
		return new TravelCalculatePremiumCoreCommand(agreement);
	}

	private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
		return coreResult.hasErrors()
				? buildResponseWithErrors(coreResult.getErrors())
				: buildSuccessfulResponse(coreResult);
	}

	private TravelCalculatePremiumResponseV1 buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
		List<ValidationError> errors = transformValidationErrorsToV1(coreErrors);
		return new TravelCalculatePremiumResponseV1(errors);
	}

	private List<ValidationError> transformValidationErrorsToV1(List<ValidationErrorDTO> coreErrors) {
		return coreErrors.stream()
				.map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
				.collect(Collectors.toList());
	}

	private TravelCalculatePremiumResponseV1 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
		AgreementDTO agreement = coreResult.getAgreement();
		TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
		response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
		response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
		response.setPersonBirthDate(agreement.getPersons().get(0).getPersonBirthDate());
		response.setAgreementDateFrom(agreement.getAgreementDateFrom());
		response.setAgreementDateTo(agreement.getAgreementDateTo());
		response.setCountry(agreement.getCountry());
		response.setMedicalRiskLimitLevel(agreement.getMedicalRiskLimitLevel());
		response.setAgreementPremium(agreement.getAgreementPremium());

		PersonDTO person = agreement.getPersons().get(0);
		List<RiskPremium> riskPremiums = person.getRisks().stream()
				.map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
				.toList();
		response.setRisks(riskPremiums);

		return response;
	}

	private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
		PersonDTO person = new PersonDTO();
		person.setPersonFirstName(request.getPersonFirstName());
		person.setPersonLastName(request.getPersonLastName());
		person.setPersonBirthDate(request.getPersonBirthDate());
		return person;
	}

	private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
		AgreementDTO agreement = new AgreementDTO();
		agreement.setAgreementDateFrom(request.getAgreementDateFrom());
		agreement.setAgreementDateTo(request.getAgreementDateTo());
		agreement.setCountry(request.getCountry());
		agreement.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
		agreement.setSelectedRisks(request.getSelectedRisks());

		PersonDTO person = buildPerson(request);
		agreement.setPersons(List.of(person));

		return agreement;
	}

}