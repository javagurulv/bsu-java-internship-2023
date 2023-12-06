package lv.javaguru.travel.insurance.dto.v1;

import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonBuilder extends DtoV1CONVERTER{
    public static PersonDto buildPerson(TravelCalculatePremiumRequestV1 request) {
        PersonDto person = new PersonDto();
        person.setPersonFirstName(request.getPersonFirstName());
        person.setPersonLastName(request.getPersonLastName());
        person.setPersonCode(request.getPersonCode());
        person.setPersonBirthDate(request.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return person;
    }
}
