package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DTOV2Converter {
    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors() ? buildResponseWithErrors(coreResult) : buildSuccessfulResponse(coreResult);
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        AgreementDTO agreement = coreResult.getAgreement();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<PersonResponseDTO> persons = agreement.getPersons().stream()
                .map(this::buildPersonFromResponse)
                .toList();
        response.setPersons(persons);
        return response;
    }

    private PersonResponseDTO buildPersonFromResponse(PersonDTO personDTO) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setPersonUUID(personDTO.getPersonUUID());
        personResponseDTO.setPersonFirstName(personDTO.getPersonFirstName());
        personResponseDTO.setPersonLastName(personDTO.getPersonLastName());
        personResponseDTO.setRisks(personDTO.getSelectedRisks().stream()
                .map(risk -> new RiskPremium(risk.getRiskIc() , risk.getPremium()))
                .toList());
        personResponseDTO.setPersonBirthDate(personDTO.getPersonBirthDate());
        personResponseDTO.setAgreementPremium(personDTO.getSelectedRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return personResponseDTO;
    }

    private TravelCalculatePremiumResponseV2 buildResponseWithErrors(TravelCalculatePremiumCoreResult coreResult) {
        List<ValidationError> errors = transformValidationErrors(coreResult.getErrors());
        return new TravelCalculatePremiumResponseV2(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> errors) {
        return errors.stream().map(error -> new ValidationError(error.getErrorCode(), error.getDescription())).toList();
    }

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setPersons(transformPersonDTOs(request));
        return agreement;
    }

    private List<PersonDTO> transformPersonDTOs(TravelCalculatePremiumRequestV2 request) {
        return request.getPersons().stream()
                .map(person -> new PersonDTO(person.getPersonUUID(), person.getPersonFirstName(), person.getPersonLastName()
                        , person.getPersonBirthDate(), null, person.getMedicalRiskLimitLevel()))
                .toList();
    }

}
