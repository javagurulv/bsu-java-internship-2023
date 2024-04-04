package lv.javaguru.travel.insurance.core.api.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;

public class ConverterV2DTO {
    public static TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV2 request) {
        return new TravelCalculatePremiumCoreCommand(buildAgreement(prepareRequest(request)));
    }


    public static TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ? buildResponseWithErrors(result) : buildSuccessfulResponse(result);
    }

    private static TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        AgreementDTO agreement = result.getAgreement();

        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setSelectedRisks(agreement.getSelectedRisks());
        response.setPersons(agreement.getPersons().stream().map(ConverterV2DTO::buildPersonResponse).toList());

        return response;
    }

    private static PersonResponseV2DTO buildPersonResponse(PersonDTO personDTO) {
        PersonResponseV2DTO response = new PersonResponseV2DTO();
        response.setPersonIc(personDTO.getPersonIc());
        response.setPersonFirstName(personDTO.getPersonFirstName());
        response.setPersonLastName(personDTO.getPersonLastName());
        response.setPersonBirthDate(personDTO.getPersonBirthDate());
        response.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        response.setSelectedRisks(personDTO.getSelectedRisks());
        response.setPersonPremium(personDTO.getPersonPremium());

        return response;
    }
    private static TravelCalculatePremiumResponseV2 buildResponseWithErrors(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(result.getErrors());
        return response;
    }

    private static AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setPersons(request.getPersons().stream().map(ConverterV2DTO::buildPerson).toList());
        return agreement;
    }

    private static TravelCalculatePremiumRequestV2 prepareRequest(TravelCalculatePremiumRequestV2 request) {
        request.getPersons().forEach(p -> p.setSelectedRisks(request.getSelectedRisks()));
        for (int i = 0; i < request.getPersons().size(); i++) {
            request.getPersons().get(i).setPersonIc("PERSON_#"+i);
        }
        return request;
    }
    private static PersonDTO buildPerson(PersonRequestV2DTO requestPerson) {
        PersonDTO person = new PersonDTO();
        person.setPersonIc(requestPerson.getPersonIc());
        person.setSelectedRisks(requestPerson.getSelectedRisks()
                .stream().map(ConverterV2DTO::buildRisk)
                .toList()
        );
        person.setPersonFirstName(requestPerson.getPersonFirstName());
        person.setPersonLastName(requestPerson.getPersonLastName());
        person.setMedicalRiskLimitLevel(requestPerson.getMedicalRiskLimitLevel());
        person.setPersonBirthDate(requestPerson.getPersonBirthDate());

        return person;
    }

    private static RiskDTO buildRisk(String riskIc) {
        return new RiskDTO(riskIc, BigDecimal.ZERO);
    }
}
