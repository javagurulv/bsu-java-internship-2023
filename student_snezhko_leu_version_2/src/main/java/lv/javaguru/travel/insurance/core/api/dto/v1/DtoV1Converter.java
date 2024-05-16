package lv.javaguru.travel.insurance.core.api.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.calculate.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.core.util.GenerateAgreementUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static lv.javaguru.travel.insurance.core.util.GeneratePersonIc.generatePersonIcs;

@Component
public class DtoV1Converter {

    @Autowired
    private GenerateAgreementUUID generateAgreementUUID;

    public TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request, TravelCalculatePremiumService service) {
        TravelCalculatePremiumCoreCommand command = buildCoreCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        return buildResponse(result);
    }

    private TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
        //request.setPersonIc("PERSON_#" + new Date().getTime());
        TravelCalculatePremiumCoreCommand command = new TravelCalculatePremiumCoreCommand();

        command.setAgreement(buildAgreement(request));

        return command;
    }
    private TravelCalculatePremiumResponseV1 buildResponse(TravelCalculatePremiumCoreResult result) {
        if (result.hasErrors()) {
            return new TravelCalculatePremiumResponseV1(result.getErrors().stream().map(this::fromErrorDTO).collect(Collectors.toList()));
        }

        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        AgreementDTO agreement = result.getAgreement();
        PersonDTO person = agreement.getPersons().get(0);

        response.setAgreementPremium(result.getAgreement().getAgreementPremium().setScale(2, RoundingMode.HALF_UP));
        response.setRisks(person.getSelectedRisks().stream().map(this::fromRiskDTO).collect(Collectors.toList()));
        response.setCountry(result.getAgreement().getCountry());
        response.setPersonBirthDate(person.getPersonBirthDate());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setCost(agreement.getCost());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setPersonFirstName(person.getPersonFirstName());
        response.setPersonLastName(person.getPersonLastName());
        response.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());
        response.setPersonIc(person.getPersonIc());
        response.setUuid(agreement.getUuid());

        return response;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(buildPerson(request)));
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setCost(request.getCost());
        //agreement.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        agreement.setSelectedRisks(request.getSelectedRisks());
        agreement.setUuid(generateAgreementUUID.generate(agreement));
        agreement.setPersons(generatePersonIcs(agreement));
        return agreement;
    }

    private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
        PersonDTO person = new PersonDTO();
//		person.setRisks(request.getRisks().stream().map(this::toRiskDTO).collect(Collectors.toList()));
        person.setPersonFirstName(request.getPersonFirstName());
        person.setPersonLastName(request.getPersonLastName());
        person.setPersonBirthDate(request.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        //person.setPersonIc();
        return person;
    }
    /*
        private RiskDTO toRiskDTO(TravelCalculatePremiumRisk risk) {
            return new RiskDTO(risk.getRiskIc(), risk.getPremium());
        }

     */
    private TravelCalculatePremiumRisk fromRiskDTO(RiskDTO risk) {
        return new TravelCalculatePremiumRisk(risk.getRiskIc(), risk.getPremium());
    }
    private ValidationError fromErrorDTO(ValidationErrorDTO error) {
        return new ValidationError(error.getErrorCode(), error.getDescription());
    }
}
