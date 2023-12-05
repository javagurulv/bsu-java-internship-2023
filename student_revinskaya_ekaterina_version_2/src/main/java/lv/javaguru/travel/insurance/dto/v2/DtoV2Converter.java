package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV2Converter {

    public TravelCalculatePremiumResponseV2 buildResponseV2fromCoreResult(TravelCalculatePremiumCoreResult result) {
        if (result.getErrors() != null) {
            return errorResponseV2FromCoreResult(result);
        }
        return successResponseV2FromFromCoreResult(result);
    }

    private TravelCalculatePremiumResponseV2 errorResponseV2FromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        return new TravelCalculatePremiumResponseV2(listOfValidationErrorFromDTO(result.getErrors()));
    }

    private List<ValidationError> listOfValidationErrorFromDTO(List<ValidationErrorDTO> validationErrorDTOS) {
        return validationErrorDTOS.stream()
                .map(validationErrorDTO -> new ValidationError
                        (validationErrorDTO.getErrorCode(), validationErrorDTO.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelCalculatePremiumResponseV2 successResponseV2FromFromCoreResult
            (TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 responseV2 = new TravelCalculatePremiumResponseV2();
        responseV2.setPersons(listPersonResponseFromPersonDTO(result.getAgreement().getPersons()));

        responseV2.setAgreementPremium(result.getAgreement().getAgreementPremium());

        responseV2.setCountry(result.getAgreement().getCountry());
        responseV2.setAgreementDateFrom(result.getAgreement().getAgreementDateFrom());
        responseV2.setAgreementDateTo(result.getAgreement().getAgreementDateTo());

        return responseV2;
    }

    public TravelCalculatePremiumCoreCommand commandFromRequestV2(TravelCalculatePremiumRequestV2 requestV2) {
        AgreementDTO agreement = buildAgreementFromRequestV2(requestV2);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    private AgreementDTO buildAgreementFromRequestV2(TravelCalculatePremiumRequestV2 requestV2) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setCountry(requestV2.getCountry());
        agreement.setSelectedRisks(requestV2.getSelectedRisks());
        agreement.setAgreementDateFrom(requestV2.getAgreementDateFrom());
        agreement.setAgreementDateTo(requestV2.getAgreementDateTo());
        agreement.setPersons(listPersonDTOFromPersonRequest(requestV2.getPersons()));
        return agreement;
    }

    private List<PersonDTO> listPersonDTOFromPersonRequest(List<PersonRequest> personRequests) {
        return personRequests != null
                ? personRequests.stream()
                .map(this::getPersonDTOFromPersonRequest)
                .collect(Collectors.toList())
                : null;

    }

    private PersonDTO getPersonDTOFromPersonRequest(PersonRequest request) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(request.getPersonFirstName());
        personDTO.setPersonLastName(request.getPersonLastName());
        personDTO.setPersonBirthDate(request.getBirthday());
        personDTO.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return personDTO;
    }

    private List<PersonResponse> listPersonResponseFromPersonDTO(List<PersonDTO> personDTOS) {
        return personDTOS.stream()
                .map(this::getPersonResponseFromPersonDTO)
                .collect(Collectors.toList());
    }

    private PersonResponse getPersonResponseFromPersonDTO(PersonDTO personDTO) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonFirstName(personDTO.getPersonFirstName());
        personResponse.setPersonLastName(personDTO.getPersonLastName());
        personResponse.setBirthday(personDTO.getPersonBirthDate());
        personResponse.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        personResponse.setPersonPremium(calculatePersonPremium(personDTO));

        personResponse.setPersonRisks(listOfTravelRiskFromDTO(personDTO.getRisks()));
        return personResponse;
    }

    private BigDecimal calculatePersonPremium(PersonDTO personDTO) {
        return personDTO.getRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<TravelRisk> listOfTravelRiskFromDTO(List<RiskDTO> riskDTOS) {
        return riskDTOS.stream()
                .map(riskDTO -> new TravelRisk(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList());
    }

}
