package lv.javaguru.travel.insurance.core.services.factory;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.api.PersonEntity;
import lv.javaguru.travel.insurance.core.domain.api.PolisEntity;
import lv.javaguru.travel.insurance.core.domain.api.RiskEntity;
import lv.javaguru.travel.insurance.core.repositories.api.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.api.PolisRepository;
import lv.javaguru.travel.insurance.core.repositories.api.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonFactorySaver {
    @Autowired
    private AgreementRepository agreementEntityRepository;
    @Autowired private EntityManagerFactorySaver personEntityFactory;
    @Autowired private RiskRepository selectedRiskEntityRepository;
    @Autowired private PolisRepository agreementPersonEntityFactory;

    public AgreementEntity createAgreementEntity(AgreementDto agreementDTO) {
        //saveAllPersons(agreementDTO);
        AgreementEntity agreementEntity = saveAgreement(agreementDTO);
        saveAllSelectedRisks(agreementDTO, agreementEntity);
        saveAllPersons(agreementDTO, agreementEntity);
        return agreementEntity;
    }

    private AgreementEntity saveAgreement(AgreementDto agreementDTO) {
        AgreementEntity agreementEntity = new AgreementEntity().builder()
                .agreementDateFrom(agreementDTO.getAgreementDateFrom())
                .agreementDateTo(agreementDTO.getAgreementDateTo())
                .country(agreementDTO.getCountry())
                .agreementPremium(agreementDTO.getAgreementPremium())
                .build();
        return agreementEntityRepository.save(agreementEntity);
    }

    private void saveAllPersons(AgreementDto agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getPersons().forEach(personDTO -> {
            PersonEntity personEntity = personEntityFactory.savePerson(personDTO);
            PolisEntity polisEntity = new PolisEntity().builder()
                    .agreement(agreementEntity)
                    .person(personEntity)
                    .medicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel())
                    .build();
            agreementPersonEntityFactory.save(polisEntity);
        });
    }

    private void saveAllSelectedRisks(AgreementDto agreementDTO,
                                      AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().forEach(riskIc -> {
            RiskEntity riskEntity = new RiskEntity().builder()
                    .agreement(agreementEntity)
                    .riskIc(riskIc)
                    .build();
            selectedRiskEntityRepository.save(riskEntity);
        });
    }
}
