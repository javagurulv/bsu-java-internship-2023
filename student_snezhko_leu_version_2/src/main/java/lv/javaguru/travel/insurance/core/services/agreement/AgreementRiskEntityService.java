package lv.javaguru.travel.insurance.core.services.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementRiskEntityDomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.AgreementRiskEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgreementRiskEntityService {
    @Autowired
    private AgreementRiskEntityRepository agreementRiskEntityRepository;

    @Autowired
    private AgreementEntityService agreementEntityService;
    public List<AgreementRiskEntityDomain> saveRisks(AgreementDTO agreementDTO) {
        return agreementDTO.getSelectedRisks().stream().map(riskIc -> {
            Optional<AgreementRiskEntityDomain> optional = agreementRiskEntityRepository.findByRiskIc(riskIc);
            if (optional.isEmpty()) {
                AgreementRiskEntityDomain domain = new AgreementRiskEntityDomain();
                domain.setRiskIc(riskIc);
                domain.setAgreement(agreementEntityService.saveAgreement(agreementDTO));
                agreementRiskEntityRepository.save(domain);
                return domain;
            }
            return optional.get();
        }).toList();
    }
}
