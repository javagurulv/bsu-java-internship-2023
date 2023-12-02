package lv.javaguru.travel.insurance.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelRisk;
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
class TravelCalculatePremiumController {
    @Autowired
    private TravelCalculatePremiumService calculatePremiumService;
    @Autowired
    private TravelCalculatePremiumRequestLogger loggerForRequest;
    @Autowired
    private TravelCalculatePremiumResponseLogger loggerForResponse;
    @Autowired
    private TravelCalculateRequestExecutionTimeLogger requestExecutionTimeLogger;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
        loggerForRequest.log(request);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreCommand command = commandFromRequestV1(request);
        TravelCalculatePremiumCoreResult result = calculatePremiumService.calculatePremium(command);
        TravelCalculatePremiumResponseV1 response = buildResponseV1fromCoreResult(result);
        stopwatch.stop();
        loggerForResponse.log(response);
        requestExecutionTimeLogger.log(stopwatch.elapsed().toMillis());
        return response;
    }

    private TravelCalculatePremiumCoreCommand commandFromRequestV1(TravelCalculatePremiumRequestV1 requestV1) {
        AgreementDTO agreement = buildAgreementFromRequestV1(requestV1);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    private AgreementDTO buildAgreementFromRequestV1(TravelCalculatePremiumRequestV1 requestV1) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setCountry(requestV1.getCountry());
        agreement.setSelectedRisks(requestV1.getSelectedRisks());
        agreement.setAgreementDateFrom(requestV1.getAgreementDateFrom());
        agreement.setAgreementDateTo(requestV1.getAgreementDateTo());
        agreement.setMedicalRiskLimitLevel(requestV1.getMedicalRiskLimitLevel());
        agreement.setPersons(List.of(getPersonDTOFromRequestV1(requestV1)));
        return agreement;
    }

    private PersonDTO getPersonDTOFromRequestV1(TravelCalculatePremiumRequestV1 requestV1) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(requestV1.getPersonFirstName());
        personDTO.setPersonLastName(requestV1.getPersonLastName());
        personDTO.setPersonBirthDate(requestV1.getBirthday());
        return personDTO;
    }

    private TravelCalculatePremiumResponseV1 buildResponseV1fromCoreResult(TravelCalculatePremiumCoreResult result) {
        if (result.getErrors() != null ) {
            return errorResponseV1FromCoreResult(result);
        }
        return successResponseV1FromFromCoreResult(result);
    }

    private TravelCalculatePremiumResponseV1 errorResponseV1FromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        return new TravelCalculatePremiumResponseV1(ListOfValidationErrorFromDTO(result.getErrors()));
    }
    private List<ValidationError> ListOfValidationErrorFromDTO(List<ValidationErrorDTO> validationErrorDTOS) {
        return validationErrorDTOS.stream()
                .map(validationErrorDTO -> new ValidationError
                        (validationErrorDTO.getErrorCode(), validationErrorDTO.getDescription()))
                .collect(Collectors.toList());
    }
    private TravelCalculatePremiumResponseV1 successResponseV1FromFromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV1 responseV1 = new TravelCalculatePremiumResponseV1();
        responseV1.setPersonFirstName(result.getAgreement().getPersons().get(0).getPersonFirstName());
        responseV1.setPersonLastName(result.getAgreement().getPersons().get(0).getPersonLastName());
        responseV1.setBirthday(result.getAgreement().getPersons().get(0).getPersonBirthDate());

        responseV1.setRisks(ListOfRisksFromDTO(result.getAgreement().getPersons().get(0).getRisks()));
        responseV1.setAgreementPremium(result.getAgreement().getAgreementPremium());

        responseV1.setCountry(result.getAgreement().getCountry());
        responseV1.setMedicalRiskLimitLevel(result.getAgreement().getMedicalRiskLimitLevel());
        responseV1.setAgreementDateFrom(result.getAgreement().getAgreementDateFrom());
        responseV1.setAgreementDateTo(result.getAgreement().getAgreementDateTo());

        return responseV1;
    }


    private List<TravelRisk> ListOfRisksFromDTO(List<RiskDTO> riskDTOS) {
        return riskDTOS.stream()
                .map(riskDTO -> new TravelRisk(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList());
    }
}