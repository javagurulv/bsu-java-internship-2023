package lv.javaguru.travel.insurance.core.api.dto.v2;

import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.util.GenerateAgreementUUID;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.travel.insurance.core.util.GeneratePersonIc.generatePersonIcs;

@Component
public class ConverterV2DTO {

    @Autowired
    private GenerateAgreementUUID generateAgreementUUID;

    public TravelCalculatePremiumCoreCommand buildCommand(TravelCalculatePremiumRequestV2 request) {
        return new TravelCalculatePremiumCoreCommand(buildAgreement(prepareRequest(request)));
    }


    public TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumCoreResult result) {
        return result.hasErrors() ? buildResponseWithErrors(result) : buildSuccessfulResponse(result);
    }

    private TravelCalculatePremiumResponseV2 buildSuccessfulResponse(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        AgreementDTO agreement = result.getAgreement();

        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setSelectedRisks(agreement.getSelectedRisks());
        response.setPersons(agreement.getPersons().stream().map(this::buildPersonResponse).toList());
        response.setUuid(agreement.getUuid());
        response.setCost(agreement.getCost());

        return response;
    }

    private PersonResponseV2DTO buildPersonResponse(PersonDTO personDTO) {
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
    private TravelCalculatePremiumResponseV2 buildResponseWithErrors(TravelCalculatePremiumCoreResult result) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(result.getErrors());
        return response;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV2 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setUuid(generateAgreementUUID.generate(agreement));
        if (request.getPersons() == null || request.getPersons().isEmpty()) {
            agreement.setPersons(List.of());
        } else{
                agreement.setPersons(request.getPersons().stream().map(this::buildPerson).toList());
            }
        agreement.setPersons(generatePersonIcs(agreement));

        agreement.setCost(request.getCost());
        return agreement;
    }

    private TravelCalculatePremiumRequestV2 prepareRequest(TravelCalculatePremiumRequestV2 request) {
        if (request.getPersons() == null || request.getPersons().isEmpty()) {
            return request;
        }

        request.getPersons().forEach(p -> p.setSelectedRisks(request.getSelectedRisks()));

        /*
        for (int i = 0; i < request.getPersons().size(); i++) {
            request.getPersons().get(i).setPersonIc("PERSON_#"+new Date().getTime());
        }
*/

        return request;
    }
    private PersonDTO buildPerson(PersonRequestV2DTO requestPerson) {
        PersonDTO person = new PersonDTO();
//        person.setPersonIc(requestPerson.getPersonIc());
        if (requestPerson.getSelectedRisks() != null && !requestPerson.getSelectedRisks().isEmpty()) {
            person.setSelectedRisks(requestPerson.getSelectedRisks()
                .stream().map(this::buildRisk)
                .toList()
        );
        }
        else {
            person.setSelectedRisks(List.of());
        }

        person.setPersonFirstName(requestPerson.getPersonFirstName());
        person.setPersonLastName(requestPerson.getPersonLastName());
        person.setMedicalRiskLimitLevel(requestPerson.getMedicalRiskLimitLevel());
        person.setPersonBirthDate(requestPerson.getPersonBirthDate());

        return person;
    }

    private RiskDTO buildRisk(String riskIc) {
        return new RiskDTO(riskIc, BigDecimal.ZERO);
    }


}
