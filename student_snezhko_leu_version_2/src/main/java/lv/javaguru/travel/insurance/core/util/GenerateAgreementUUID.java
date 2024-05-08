package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class GenerateAgreementUUID {
    public UUID generate(AgreementDTO agreement) {
     //   return UUID.fromString();
//        return UUID.fromString(agreement.getAgreementDateTo().toString()
//                + agreement.getAgreementDateFrom().toString()
//                );
        return UUID.randomUUID();
//        return UUID.fromString(agreement.toString());
        //UUID.fromString()
        //return "AGR_UUID_#" + agreement.getAgreementDateTo() + agreement.getAgreementDateFrom() + agreement.getPersons().size();
    }
}
