package lv.javaguru.travel.insurance.core.services.savers.entity_savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.domain.SelectedRisk;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectedRiskSaver {
    @Autowired
    SelectedRiskRepository selectedRiskRepository;
    private SelectedRisk convertToSelectedRisk(String riskIc, Agreement agreement) {
        SelectedRisk selectedRisk = new SelectedRisk();
        selectedRisk.setAgreementId(agreement);
        selectedRisk.setRiskIc(riskIc);
        return selectedRisk;
    }

    private void saveNotExistRisk(String riskIc, Agreement agreement) {
        SelectedRisk selectedRisk = convertToSelectedRisk(riskIc, agreement);
        if (notExist(selectedRisk)) {
            selectedRiskRepository.save(selectedRisk);
        }
    }

    public void saveRisks(AgreementDTO agreementDTO, Agreement agreement) {
        agreementDTO.getSelectedRisks().stream()
                .forEach(risk -> saveNotExistRisk(risk, agreement));

    }
    private boolean notExist(SelectedRisk selectedRisk) {
        return selectedRiskRepository.findByAgreementIdAndRiskIc(selectedRisk.getAgreementId(), selectedRisk.getRiskIc())
                .isEmpty();
    }
}
