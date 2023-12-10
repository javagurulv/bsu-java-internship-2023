package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insurance/travel/api/v1/")
public class TravelCalculatePremiumControllerV1 {

	private final TravelCalculatePremiumRequestLoggerV1 requestLogger;
	private final TravelCalculatePremiumResponseLoggerV1 responseLogger;
	private final TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
	private final TravelCalculatePremiumService calculatePremiumService;

	@Autowired
	public TravelCalculatePremiumControllerV1(
			TravelCalculatePremiumRequestLoggerV1 requestLogger,
			TravelCalculatePremiumResponseLoggerV1 responseLogger,
			TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger,
			TravelCalculatePremiumService calculatePremiumService) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
		this.executionTimeLogger = executionTimeLogger;
		this.calculatePremiumService = calculatePremiumService;
	}

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
		AgreementDto agreement = buildAgreement(request);
		return new TravelCalculatePremiumCoreCommand(agreement);
	}

	private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
		return coreResult.hasErrors()
				? buildResponseWithErrors(coreResult.getErrors())
				: buildSuccessfulResponse(coreResult);
	}

	private List<ValidationError> transformValidationErrorsToV1(List<ValidationErrorDto> coreErrors) {
		return coreErrors.stream()
				.map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
				.collect(Collectors.toList());
	}

	private TravelCalculatePremiumResponseV1 buildResponseWithErrors(List<ValidationErrorDto> coreErrors) {
		List<ValidationError> errors = transformValidationErrorsToV1(coreErrors);
		return new TravelCalculatePremiumResponseV1(errors);
	}

	private TravelCalculatePremiumResponseV1 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
		AgreementDto agreement = coreResult.getAgreement();
		TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();

		if (!coreResult.hasErrors()) {
			setAgreementDetails(response, agreement);
			setPersonAndRiskDetails(response, agreement);
		}

		return response;
	}
	private void setAgreementDetails(TravelCalculatePremiumResponseV1 response, AgreementDto agreement) {
		response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
		response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
		response.setPersonCode(agreement.getPersons().get(0).getPersonCode());
		response.setPersonBirthDate(agreement.getPersons().get(0).getPersonBirthDate());
		response.setAgreementDateFrom(agreement.getAgreementDateFrom());
		response.setAgreementDateTo(agreement.getAgreementDateTo());
		response.setCountry(agreement.getCountry());
		response.setMedicalRiskLimitLevel(agreement.getPersons().get(0).getMedicalRiskLimitLevel());
		response.setAgreementPremium(agreement.getAgreementPremium());
	}

	private void setPersonAndRiskDetails(TravelCalculatePremiumResponseV1 response, AgreementDto agreement) {
		PersonDto person = agreement.getPersons().get(0);
		List<RiskPremium> riskPremiums = person.getRisks().stream()
				.map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
				.toList();
		response.setRisks(riskPremiums);
	}

	private PersonDto buildPerson(TravelCalculatePremiumRequestV1 request) {
		PersonDto person = new PersonDto();
		person.setPersonFirstName(request.getPersonFirstName());
		person.setPersonLastName(request.getPersonLastName());
		person.setPersonCode(request.getPersonCode());
		person.setPersonBirthDate(request.getPersonBirthDate());
		person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
		return person;
	}

	private AgreementDto buildAgreement(TravelCalculatePremiumRequestV1 request) {
		AgreementDto agreement = new AgreementDto();
		agreement.setAgreementDateFrom(request.getAgreementDateFrom());
		agreement.setAgreementDateTo(request.getAgreementDateTo());
		agreement.setCountry(request.getCountry());
		agreement.setSelectedRisks(request.getSelected_risks());
		PersonDto person = buildPerson(request);
		agreement.setPersons(List.of(person));
		return agreement;
	}
}
