package lv.javaguru.travel.insurance.dto.common;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PersonDTOConverter {
    public PersonResponseDTO buildPersonResponse(PersonDTO personDTO) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setPersonUUID(personDTO.getPersonUUID());
        personResponseDTO.setPersonFirstName(personDTO.getPersonFirstName());
        personResponseDTO.setPersonLastName(personDTO.getPersonLastName());
        personResponseDTO.setPersonRisks(personDTO.getSelectedRisks().stream()
                .map(risk -> new RiskPremium(risk.getRiskIc() , risk.getPremium()))
                .toList());
        personResponseDTO.setPersonBirthDate(personDTO.getPersonBirthDate());
        personResponseDTO.setPersonPremium(personDTO.getSelectedRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        personResponseDTO.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        personResponseDTO.setTravelCost(personDTO.getTravelCost());
        return personResponseDTO;
    }
}
