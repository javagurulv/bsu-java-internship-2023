package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV1Converter {
    public TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request, TravelCalculatePremiumService service) {
        TravelCalculatePremiumCoreCommand command = buildCoreCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        return buildResponse(result);
    }

    private TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV1 request) {
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
        response.setRisks(person.getPersonRisks().stream().map(this::fromRiskDTO).collect(Collectors.toList()));
        response.setCountry(result.getAgreement().getCountry());
        response.setPersonBirthDate(person.getPersonBirthDate());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementPremium(agreement.getAgreementPremium());
        response.setPersonFirstName(person.getPersonFirstName());
        response.setPersonLastName(person.getPersonLastName());
        response.setMedicalRiskLimitLevel(person.getMedicalRiskLimitLevel());

        return response;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setPersons(List.of(buildPerson(request)));
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        //agreement.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        agreement.setSelectedRisks(request.getSelectedRisks());
        return agreement;
    }

    private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
        PersonDTO person = new PersonDTO();
//		person.setRisks(request.getRisks().stream().map(this::toRiskDTO).collect(Collectors.toList()));
        person.setPersonFirstName(request.getPersonFirstName());
        person.setPersonLastName(request.getPersonLastName());
        person.setPersonBirthDate(request.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
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
