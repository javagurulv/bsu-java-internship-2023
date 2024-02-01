package lv.javaguru.travel.insurance.core.services.factory;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.domain.api.*;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRiskRepository;
import lv.javaguru.travel.insurance.core.repositories.api.PolisRepository;
import lv.javaguru.travel.insurance.core.repositories.api.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonFactorySaver {
    @Autowired private AgreementRepository agreementEntityRepository;
    @Autowired private EntityManagerFactorySaver personEntityFactory;
    @Autowired private RiskRepository selectedRiskEntityRepository;
    @Autowired private PolisRepository agreementPersonEntityRepository;
    @Autowired private AgreementRiskRepository agreementPersonRiskEntityRepository;

    public AgreementEntity createAgreementEntity(AgreementDto agreementDto) {
        AgreementEntity agreementEntity = saveAgreement(agreementDto);
        saveAllSelectedRisks(agreementDto, agreementEntity);
        saveAllAgreementPersons(agreementDto, agreementEntity);
        return agreementEntity;
    }

    private AgreementEntity saveAgreement(AgreementDto agreementDto) {
        AgreementEntity agreementEntity = new AgreementEntity().builder()
                        .agreementDateFrom(agreementDto.getAgreementDateFrom())
                        .agreementDateTo(agreementDto.getAgreementDateTo())
                        .country(agreementDto.getCountry())
                        .agreementPremium(agreementDto.getAgreementPremium())
                        .uuid(UUID.randomUUID().toString())
                        .build();
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllSelectedRisks(AgreementDto agreementDto, AgreementEntity agreementEntity) {
        agreementDto.getSelectedRisks().forEach(riskIc -> {
            SelectedRiskEntity riskEntity = new SelectedRiskEntity().builder()
                            .agreement(agreementEntity)
                                    .riskIc(riskIc)
                                    .build();
            selectedRiskEntityRepository.save(riskEntity);
        });
    }

    private void saveAllAgreementPersons(AgreementDto agreementDTO,
                                         AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(personDTO -> {
            PersonEntity personEntity = personEntityFactory.savePerson(personDTO);
            AgreementPersonEntity agreementPersonEntity = saveAgreementPerson(agreementEntity, personDTO, personEntity);
            saveAllPersonRisks(personDTO, agreementPersonEntity);
        });
    }

    private AgreementPersonEntity saveAgreementPerson(AgreementEntity agreementEntity, PersonDto personDTO, PersonEntity personEntity) {
        AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity().builder()
                .agreement(agreementEntity)
                .person(personEntity)
                        .medicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel())
                        .build();
        return agreementPersonEntity = agreementPersonEntityRepository.save(agreementPersonEntity);
    }

    private void saveAllPersonRisks(PersonDto personDTO,
                                    AgreementPersonEntity agreementPersonEntity) {
        personDTO.getRisks().forEach(riskDTO -> {
            AgreementPersonRiskEntity agreementPersonRiskEntity = new AgreementPersonRiskEntity().builder()
                    .agreementPerson(agreementPersonEntity)
                    .riskIc(riskDTO.getRiskIc())
                    .premium(riskDTO.getPremium())
                    .build();
            agreementPersonRiskEntityRepository.save(agreementPersonRiskEntity);
        });
    }

}
