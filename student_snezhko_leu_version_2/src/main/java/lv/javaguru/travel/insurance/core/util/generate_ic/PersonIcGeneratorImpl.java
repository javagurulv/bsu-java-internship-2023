package lv.javaguru.travel.insurance.core.util.generate_ic;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import org.springframework.stereotype.Component;

@Component
public class PersonIcGeneratorImpl implements PersonIcGenerator{

    public String generate(AgreementEntityDomain agreement, PersonDTO person) {
        StringBuilder sb = new StringBuilder();
        sb.append("PERSON_IC_").append("AGR_#").append(agreement.getId()).append("_")
                .append(person.getPersonLastName())
                .append("_")
                .append(person.getPersonFirstName());

        //person.setPersonIc(sb.toString());
        return sb.toString();
    }
}
