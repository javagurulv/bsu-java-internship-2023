package lv.javaguru.travel.insurance.core.util.generate_ic;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import org.springframework.stereotype.Component;

@Component
public interface PersonIcGenerator {
    public String generate(AgreementEntityDomain agreement, PersonDTO person);
}
