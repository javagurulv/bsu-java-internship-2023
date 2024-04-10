package lv.javaguru.travel.insurance.core.services.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import lv.javaguru.travel.insurance.core.repositories.agreement.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AgreementEntityService {
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;

    public AgreementEntityDomain saveAgreement(AgreementDTO agreement) {
        AgreementEntityDomain domain = new AgreementEntityDomain();
        domain.setDateFrom(new Date(agreement.getAgreementDateFrom().getTime()));
        domain.setDateTo(new Date(agreement.getAgreementDateTo().getTime()));
        domain.setCountry(agreement.getCountry());
        domain.setPremium(agreement.getAgreementPremium());
        agreementEntityRepository.save(domain);
        return domain;
    }
}
