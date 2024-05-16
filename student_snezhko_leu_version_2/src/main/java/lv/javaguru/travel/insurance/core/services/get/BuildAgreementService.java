package lv.javaguru.travel.insurance.core.services.get;

import lv.javaguru.travel.insurance.core.RISKS;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.*;
import lv.javaguru.travel.insurance.core.repositories.agreement.AgreementPersonEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.agreement.AgreementRiskEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.agreement.PersonRiskEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.get.GetAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BuildAgreementService {


    @Autowired
    private AgreementRiskEntityRepository riskEntityRepository;


    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;

    @Autowired
    private PersonRiskEntityRepository personRiskEntityRepository;
    public AgreementDTO buildAgreement(AgreementEntityDomain agreementEntityDomain) {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementEntityDomain.getDateFrom());
        agreementDTO.setAgreementDateTo(agreementEntityDomain.getDateTo());
        agreementDTO.setCountry(agreementEntityDomain.getCountry());
        agreementDTO.setUuid(agreementEntityDomain.getUuid());
        agreementDTO.setSelectedRisks(
                buildSelectedRisks(agreementEntityDomain)
        );
        agreementDTO.setAgreementPremium(agreementEntityDomain.getPremium());

        agreementDTO.setPersons(
                buildPersons(agreementEntityDomain)
        );
        agreementDTO.setCost(agreementEntityDomain.getCost());
        return agreementDTO;
    }

    private List<String> buildSelectedRisks(AgreementEntityDomain agreementEntityDomain) {
        List<String> risks = Arrays.stream(RISKS.values())
                .map(risk ->
                {
                    return riskEntityRepository.findByIcAndAgreement(risk.name(), agreementEntityDomain);
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(AgreementRiskEntityDomain::getRiskIc)
                .toList();
        return risks;
    }

    private List<PersonDTO> buildPersons(AgreementEntityDomain agreementEntityDomain) {
        return agreementPersonEntityRepository.findByAgreement(agreementEntityDomain).stream()
                .map(agreementPerson -> {
                    PersonDTODomain personDomain = agreementPerson.getPerson();
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonFirstName(personDomain.getPersonFirstName());
                    personDTO.setPersonLastName(personDomain.getPersonLastName());
                    personDTO.setPersonBirthDate(personDomain.getPersonBirthDate());
                    personDTO.setMedicalRiskLimitLevel(agreementPerson.getMedicalRiskLimitLevel());
                    personDTO.setPersonIc(personDomain.getPersonIc());
                    personDTO.setPersonPremium(agreementPerson.getPremium());
                    personDTO.setSelectedRisks(buildPersonRisks(agreementPerson));
                    return personDTO;
                }).toList();
    }

    private List<RiskDTO> buildPersonRisks(AgreementPersonEntityDomain agreementPerson) {
        return Arrays.stream(RISKS.values())
                .map(riskIc ->
                {
                    return personRiskEntityRepository.findByIcAndPerson(
                        riskIc.name(),
                        agreementPerson
                    );
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(riskDomain -> {
                    RiskDTO risk = new RiskDTO();
                    risk.setRiskIc(riskDomain.getRiskIc());
                    risk.setPremium(riskDomain.getPremium());
                    return risk;
                })
                .toList();
    }
}
