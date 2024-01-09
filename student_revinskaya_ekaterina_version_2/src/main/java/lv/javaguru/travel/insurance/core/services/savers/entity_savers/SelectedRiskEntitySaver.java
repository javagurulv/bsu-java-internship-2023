package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.SelectedRisk;
import lv.javaguru.travel.insurance.core.repositories.entity.SelectedRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectedRiskEntitySaver {
    @Autowired
    SelectedRiskRepository selectedRiskRepository;
    private SelectedRisk convertToSelectedRiskEntity(String riskIc, AgreementEntity agreementEntity) {
        SelectedRisk selectedRisk = new SelectedRisk();
        selectedRisk.setAgreementEntityId(agreementEntity);
        selectedRisk.setRiskIc(riskIc);
        return selectedRisk;
    }

    private void saveNotExistRiskEntity(String riskIc, AgreementEntity agreementEntity) {
        SelectedRisk selectedRisk = convertToSelectedRiskEntity(riskIc, agreementEntity);
        if (notExist(selectedRisk)) {
            selectedRiskRepository.save(selectedRisk);
        }
    }

    public void saveRisksEntity(AgreementDTO agreementDTO, AgreementEntity agreementEntity) {
        agreementDTO.getSelectedRisks().stream()
                .forEach(risk -> saveNotExistRiskEntity(risk, agreementEntity));

    }
    private boolean notExist(SelectedRisk selectedRisk) {
        return selectedRiskRepository.findByAgreementIdAndRiskIc(selectedRisk.getAgreementEntityId(), selectedRisk.getRiskIc())
                .isEmpty();
    }
}
