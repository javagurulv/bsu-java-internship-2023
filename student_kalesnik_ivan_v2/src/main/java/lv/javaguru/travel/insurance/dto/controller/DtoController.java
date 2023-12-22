package lv.javaguru.travel.insurance.dto.controller;

import lv.javaguru.travel.insurance.core.api.command.TravelCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoController {


    public static TravelCoreCommand buildCoreCommand(String uuid) {
        return new TravelCoreCommand(uuid);
    }

    public TravelAgreementResponseGetter buildResponse(TravelCoreResult coreResult) {
        if (coreResult.hasErrors()) {
            return buildResponseWithErrors(coreResult.getErrors());
        } else {
            return buildSuccessfulResponse(coreResult);
        }
    }

    private TravelAgreementResponseGetter buildResponseWithErrors(List<ValidationErrorDto> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new TravelAgreementResponseGetter(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDto> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelAgreementResponseGetter buildSuccessfulResponse(TravelCoreResult coreResult) {
        AgreementDto agreement = coreResult.getAgreement();
        TravelAgreementResponseGetter response = new TravelAgreementResponseGetter().builder()
                .uuid(agreement.getUuid())
                .agreementDateFrom(agreement.getAgreementDateFrom())
                .agreementDateTo(agreement.getAgreementDateTo())
                .country(agreement.getCountry())
                .agreementPremium(agreement.getAgreementPremium())
                .build();

        List<PersonResponseDto> personResponseDTOS = agreement.getPersons().stream()
                .map(this::buildPersonFromResponse)
                .collect(Collectors.toList());
        response.setPersons(personResponseDTOS);

        return response;
    }

    private PersonResponseDto buildPersonFromResponse(PersonDto personDTO) {
        PersonResponseDto person = new PersonResponseDto().builder()
                .personFirstName(personDTO.getPersonFirstName())
                .personLastName(personDTO.getPersonLastName())
                .personCode(personDTO.getPersonCode())
                .personBirthDate(personDTO.getPersonBirthDate())
                .medicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel())
                .build();

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

}
