package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.javaguru.travel.insurance.dto.v1.PersonBuilder.buildPerson;

@Component
public class AgreementBuilder extends DtoV1CONVERTER{
    public static AgreementDto buildAgreement(TravelCalculatePremiumRequestV1 request) {
        //
        AgreementDto agreement = new AgreementDto();
        //
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelected_risks());

        PersonDto person = buildPerson(request);
        agreement.setPersons(List.of(person));
        return agreement;
    }
}
