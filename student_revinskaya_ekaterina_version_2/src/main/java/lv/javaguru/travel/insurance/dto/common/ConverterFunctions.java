package lv.javaguru.travel.insurance.dto.common;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v2.PersonRequest;
import lv.javaguru.travel.insurance.dto.v2.PersonResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ConverterFunctions {
    public List<PersonResponse> listPersonResponseFromPersonDTO(List<PersonDTO> personDTOS) {
        return personDTOS.stream()
                .map(this::getPersonResponseFromPersonDTO)
                .collect(Collectors.toList());
    }

    private PersonResponse getPersonResponseFromPersonDTO(PersonDTO personDTO) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonFirstName(personDTO.getPersonFirstName());
        personResponse.setPersonLastName(personDTO.getPersonLastName());
        personResponse.setBirthday(personDTO.getPersonBirthDate());
        personResponse.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        personResponse.setPersonPremium(calculatePersonPremium(personDTO));
        personResponse.setPersonalCode(personDTO.getPersonalCode());
        personResponse.setPersonRisks(listOfTravelRiskFromDTO(personDTO.getRisks()));
        return personResponse;
    }
    private BigDecimal calculatePersonPremium(PersonDTO personDTO) {
        return personDTO.getRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<TravelRisk> listOfTravelRiskFromDTO(List<RiskDTO> riskDTOS) {
        return riskDTOS.stream()
                .map(riskDTO -> new TravelRisk(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList());
    }
    public List<ValidationError> listOfValidationErrorFromDTO(List<ValidationErrorDTO> validationErrorDTOS) {
        return validationErrorDTOS.stream()
                .map(validationErrorDTO -> new ValidationError
                        (validationErrorDTO.getErrorCode(), validationErrorDTO.getDescription()))
                .collect(Collectors.toList());
    }
    public List<TravelRisk> listOfRisksFromDTO(List<RiskDTO> riskDTOS) {
        return riskDTOS.stream()
                .map(riskDTO -> new TravelRisk(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList());
    }
    public List<PersonDTO> listPersonDTOFromPersonRequest(List<PersonRequest> personRequests) {
        return personRequests != null
                ? personRequests.stream()
                .map(this::getPersonDTOFromPersonRequest)
                .collect(Collectors.toList())
                : null;

    }

    private PersonDTO getPersonDTOFromPersonRequest(PersonRequest request) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(request.getPersonFirstName());
        personDTO.setPersonLastName(request.getPersonLastName());
        personDTO.setPersonBirthDate(request.getBirthday());
        personDTO.setPersonalCode(request.getPersonalCode());
        personDTO.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return personDTO;
    }
}
