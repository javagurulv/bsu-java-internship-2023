package lv.javaguru.travel.insurance.dto.common;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRiskEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class EntitiesToDtoConverter {
    public AgreementDTO transformAgreementEntity(AgreementEntity agreementEntity) {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementEntity.getAgreementDateFrom());
        agreementDTO.setAgreementDateTo(agreementEntity.getAgreementDateTo());
        agreementDTO.setAgreementPremium(agreementEntity.getAgreementPremium());
        agreementDTO.setUuid(agreementEntity.getUuid());
        agreementDTO.setCountry(agreementEntity.getCountry());
        return agreementDTO;
    }

    public RiskDTO transformRiskEntity(AgreementPersonRiskEntity agreementPersonRiskEntity) {
        RiskDTO riskDTO = new RiskDTO();
        riskDTO.setPremium(agreementPersonRiskEntity.getPremium());
        riskDTO.setRiskIc(agreementPersonRiskEntity.getRiskIc());
        return riskDTO;
    }

    public PersonDTO transformPersonEntity(PersonEntity personEntity, AgreementPersonEntity agreementPersonEntity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonUUID(personEntity.getPersonCode());
        personDTO.setPersonFirstName(personEntity.getFirstName());
        personDTO.setPersonLastName(personEntity.getLastName());
        personDTO.setPersonBirthDate(personEntity.getBirthDate());
        personDTO.setTravelCost(agreementPersonEntity.getTravelCost());
        return personDTO;
    }
}
