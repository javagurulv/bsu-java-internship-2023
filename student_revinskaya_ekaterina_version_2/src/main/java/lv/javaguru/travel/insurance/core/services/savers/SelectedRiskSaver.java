package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.SelectedRisk;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SelectedRiskSaver {
    @Autowired
    SelectedRiskRepository selectedRiskRepository;

    private SelectedRisk convertToSelectedRisk(RiskDTO riskDTO) {
        SelectedRisk selectedRisk = new SelectedRisk();
        selectedRisk.setRiskAgreement(riskDTO.getPremium());
        selectedRisk.setRiskIc(riskDTO.getRiskIc());
        return selectedRisk;
    }

    private void saveNotExistRisk(RiskDTO riskDTO) {
        SelectedRisk selectedRisk = convertToSelectedRisk(riskDTO);
      //  if(notExist(selectedRisk)) {
            selectedRiskRepository.save(selectedRisk);
      //  }
    }
    public void saveNotAlreadyExistRisks(AgreementDTO agreementDTO) {
        agreementDTO.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .forEach(this::saveNotExistRisk);

    }
//    private boolean notExist(SelectedRisk selectedRisk) {
//        return selectedRiskRepository.findAll(Example.of(selectedRisk))
//                .isEmpty();
//    }


}

