package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.internal.TravelGetPolicyCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entity.*;
import lv.javaguru.travel.insurance.core.repositories.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseOnEntityBuilder {
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private PersonAgreementRepository personAgreementRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SelectedRiskRepository selectedRiskRepository;
    @Autowired
    private PersonAgreementRiskRepository personAgreementRiskRepository;

    public TravelGetPolicyCoreResult buildResponse(String uuid) {
        TravelGetPolicyCoreResult result = new TravelGetPolicyCoreResult();
        Agreement agreement = agreementRepository.findByUuid(uuid).get();
        List<SelectedRisk> risks = selectedRiskRepository.findByAgreementId(agreement);
        List<PersonAgreement> personAgreements = personAgreementRepository.findByAgreementId(agreement);
        result.setAgreement(buildAgreementDTO(agreement, risks, personAgreements));
        return result;
    }

    private AgreementDTO buildAgreementDTO(Agreement agreement, List<SelectedRisk> risks, List<PersonAgreement> personAgreements) {
        return AgreementDTO.builder()
                .uuid(agreement.getUuid())
                .travelCost(agreement.getTravelCost())
                .agreementDateFrom(agreement.getDateFrom())
                .agreementDateTo(agreement.getDateTo())
                .selectedRisks(selectedRisksListFromEntity(risks))
                .country(agreement.getCountry())
                .agreementPremium(agreement.getPremium())
                .persons(listPersonDTOFromEntityList(personAgreements))
                .build();
    }

    private List<PersonDTO> listPersonDTOFromEntityList(List<PersonAgreement> personAgreements) {
        return personAgreements.stream()
                .map(personAgreement -> personDTOFromPerson(personAgreement.getPersonId(), personAgreement))
                .collect(Collectors.toList());
    }

    private PersonDTO personDTOFromPerson(Person person, PersonAgreement personAgreement) {
        return PersonDTO.builder()
                .personalCode(person.getPersonalCode())
                .personFirstName(person.getFirstName())
                .personLastName(person.getLastName())
                .personBirthDate(person.getBirthday())
                .medicalRiskLimitLevel(personAgreement.getMedicalRiskLimitLevel())
                .risks(listRiskRTO(personAgreementRiskRepository.findByPersonAgreementId(personAgreement)))
                .build();
    }

    private List<RiskDTO> listRiskRTO(List<PersonAgreementRisk> personAgreementRisks) {
        return personAgreementRisks.stream()
                .map(personAgreementRisk -> new RiskDTO(
                        personAgreementRisk.getRiskIc(), personAgreementRisk.getPremium()))
                .collect(Collectors.toList());
    }

    private List<String> selectedRisksListFromEntity(List<SelectedRisk> selectedRisks) {
        return selectedRisks.stream()
                .map(SelectedRisk::getRiskIc)
                .collect(Collectors.toList());
    }
}
