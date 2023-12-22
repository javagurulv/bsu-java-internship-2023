package lv.javaguru.travel.insurance.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV2CONVERTER {
    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV2 request) {
        AgreementDto agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        if (coreResult.hasErrors()) {
            return buildResponseWithErrors(coreResult.getErrors());
        } else {
            return buildSuccessfulResponse(coreResult);
        }
    }

    private static TravelCalculatePremiumResponseV2 buildResponseWithErrors(List<ValidationErrorDto> coreErrors) {
        List<ValidationError> errors = transformValidationErrorsToV2(coreErrors);
        return new TravelCalculatePremiumResponseV2(errors);
    }

    private static List<ValidationError> transformValidationErrorsToV2(List<ValidationErrorDto> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        AgreementDto agreement = coreResult.getAgreement();
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<PersonResponseDto> personResponseDTOS = agreement.getPersons().stream()
                .map(this::buildPersonFromResponse)
                .toList();
        response.setPersons(personResponseDTOS);

        return response;
    }

    private PersonResponseDto buildPersonFromResponse(PersonDto personDTO) {
        PersonResponseDto person = new PersonResponseDto();
        person.setPersonFirstName(personDTO.getPersonFirstName());
        person.setPersonLastName(personDTO.getPersonLastName());
        person.setPersonCode(personDTO.getPersonCode());
        person.setPersonBirthDate(personDTO.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());

        BigDecimal personPremium = personDTO.getRisks().stream()
                .map(RiskDto::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        person.setPersonPremium(personPremium);

        List<RiskPremium> personRisks = personDTO.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList());
        person.setPerson_risks(personRisks);

        return person;
    }

    private PersonDto buildPersonFromRequest(PersonRequestDto personRequestDTO) {
        PersonDto person = new PersonDto().builder()
                .personFirstName(personRequestDTO.getPersonFirstName())
                .personLastName(personRequestDTO.getPersonLastName())
                .personCode(personRequestDTO.getPersonCode())
                .personBirthDate(personRequestDTO.getPersonBirthDate())
                .medicalRiskLimitLevel(personRequestDTO.getMedicalRiskLimitLevel())
                .build();
        return person;
    }

    private AgreementDto buildAgreement(TravelCalculatePremiumRequestV2 request) {
        AgreementDto agreement = new AgreementDto().builder()
                        .agreementDateFrom(request.getAgreementDateFrom())
                        .agreementDateTo(request.getAgreementDateTo())
                        .country(request.getCountry())
                        .selectedRisks(request.getSelectedRisks())
                        .build();
        agreement.setPersons(personRequestList(request));

        return agreement;
    }

    private List<PersonDto> personRequestList(TravelCalculatePremiumRequestV2 request) {
        if (request.getPersons() == null) {
            return List.of();
        } else {
            return request.getPersons().stream()
                    .map(this::buildPersonFromRequest)
                    .collect(Collectors.toList());
        }
    }
}
