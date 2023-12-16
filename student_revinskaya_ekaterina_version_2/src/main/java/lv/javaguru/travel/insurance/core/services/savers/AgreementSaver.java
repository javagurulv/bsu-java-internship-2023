package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.domain.SelectedRisk;
import lv.javaguru.travel.insurance.core.repositories.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AgreementSaver {
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    SelectedRiskRepository selectedRiskRepository;
    public void saveAgreements(AgreementDTO agreementDTO) {
        Agreement agreement = convertToAgreement(agreementDTO);
        agreement = saveAgreement(agreement);
        saveRisks(agreementDTO, agreement);
    }


    private Agreement convertToAgreement(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }

    private Agreement saveAgreement(Agreement agreement) {
            return agreementRepository.save(agreement);
    }
    private SelectedRisk convertToSelectedRisk(RiskDTO riskDTO, Agreement agreement) {
        SelectedRisk selectedRisk = new SelectedRisk();
        selectedRisk.setAgreementId(agreement);
        selectedRisk.setRiskAgreement(riskDTO.getPremium());
        selectedRisk.setRiskIc(riskDTO.getRiskIc());
        return selectedRisk;
    }

    private void saveNotExistRisk(RiskDTO riskDTO, Agreement agreement) {
        SelectedRisk selectedRisk = convertToSelectedRisk(riskDTO, agreement);
        if(notExist(selectedRisk)) {
            selectedRiskRepository.save(selectedRisk);
        }
    }
    private void saveRisks(AgreementDTO agreementDTO, Agreement agreement) {
        agreementDTO.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .forEach(riskDTO -> saveNotExistRisk(riskDTO, agreement));

    }
    private boolean notExist(SelectedRisk selectedRisk) {
        return selectedRiskRepository.findByAgreementIdAndRiskIc(selectedRisk.getAgreementId(), selectedRisk.getRiskIc())
                .isEmpty();
    }
}
