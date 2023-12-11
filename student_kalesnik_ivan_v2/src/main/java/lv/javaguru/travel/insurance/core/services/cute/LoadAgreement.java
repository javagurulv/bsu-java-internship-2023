package lv.javaguru.travel.insurance.core.services.cute;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;

import lv.javaguru.travel.insurance.core.domain.api.*;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRiskRepository;
import lv.javaguru.travel.insurance.core.repositories.api.PolisRepository;
import lv.javaguru.travel.insurance.core.repositories.api.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class LoadAgreement {

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private RiskRepository selectedRiskRepository;

    @Autowired
    private PolisRepository polisRepository;

    @Autowired
    private AgreementRiskRepository agreementRiskRepository;

    AgreementDto load(String uuid) {
        AgreementEntity agreement = agreementRepository.findByUuid(uuid).orElseThrow();
        AgreementDto dto = createAgreementDTO(agreement);
        loadPersons(dto, agreement);
        loadSelectedRisks(dto, agreement);
        return dto;
    }

    private AgreementDto createAgreementDTO(AgreementEntity agreement) {
        AgreementDto dto = new AgreementDto();
        loadAgreementFields(dto, agreement);
        return dto;
    }

    private void loadPersons(AgreementDto dto, AgreementEntity agreement) {
        List<PersonDto> persons = polisRepository.findByAgreement(agreement)
                .stream()
                .map(this::createPersonDTO)
                .collect(Collectors.toList());
        dto.setPersons(persons);
    }

    private PersonDto createPersonDTO(PolisEntity personEntity) {
        PersonDto personDTO = new PersonDto();
        loadPersonFields(personDTO, personEntity);
        loadPersonRisks(personDTO, personEntity);
        return personDTO;
    }

    private void loadPersonFields(PersonDto personDTO, PolisEntity personEntity) {
        personDTO.setPersonFirstName(personEntity.getPerson().getFirstName());
        personDTO.setPersonLastName(personEntity.getPerson().getLastName());
        personDTO.setPersonCode(personEntity.getPerson().getPersonCode());
        personDTO.setPersonBirthDate(personEntity.getPerson().getBirthDate());
        personDTO.setMedicalRiskLimitLevel(personEntity.getMedicalRiskLimitLevel());
    }

    private void loadPersonRisks(PersonDto personDTO, PolisEntity personEntity) {
        List<RiskDto> risks = agreementRiskRepository.findByAgreementPerson(personEntity)
                .stream()
                .map(this::createRiskDTO)
                .collect(Collectors.toList());
        personDTO.setRisks(risks);
    }

    private RiskDto createRiskDTO(AgreementRiskEntity agreementPersonRiskEntity) {
        return new RiskDto().builder()
                .riskIc(agreementPersonRiskEntity.getRiskIc())
                .premium(agreementPersonRiskEntity.getPremium())
                .build();
    }

    private void loadSelectedRisks(AgreementDto dto, AgreementEntity agreement) {
        List<String> selectedRisks = selectedRiskRepository.findByAgreement(agreement)
                .stream()
                .map(RiskEntity::getRiskIc)
                .collect(Collectors.toList());
        dto.setSelectedRisks(selectedRisks);
    }

    private void loadAgreementFields(AgreementDto dto, AgreementEntity agreement) {
        dto.setUuid(agreement.getUuid());
        dto.setAgreementDateFrom(agreement.getAgreementDateFrom());
        dto.setAgreementDateTo(agreement.getAgreementDateTo());
        dto.setCountry(agreement.getCountry());
        dto.setAgreementPremium(agreement.getAgreementPremium());
    }
}

