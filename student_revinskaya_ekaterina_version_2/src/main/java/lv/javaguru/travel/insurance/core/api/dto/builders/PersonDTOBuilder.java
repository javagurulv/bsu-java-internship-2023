package lv.javaguru.travel.insurance.core.api.dto.builders;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private Date personBirthDate;

    private Long personalCode;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks = new ArrayList<>();

    public static PersonDTOBuilder createPersonDTO() {
        return new PersonDTOBuilder();
    }

    public PersonDTO build() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(personFirstName);
        personDTO.setPersonLastName(personLastName);
        personDTO.setPersonBirthDate(personBirthDate);
        personDTO.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
        personDTO.setPersonalCode(personalCode);
        personDTO.setRisks(risks);
        return personDTO;
    }

    public PersonDTOBuilder withFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public PersonDTOBuilder withLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public PersonDTOBuilder withBirthDate(Date personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withRisk(RiskDTO riskDTO) {
        this.risks.add(riskDTO);
        return this;
    }

    public PersonDTOBuilder withRisk(RiskDTOBuilder riskDTOBuilder) {
        this.risks.add(riskDTOBuilder.build());
        return this;
    }
    public PersonDTOBuilder withPersonalCode(Long personalCode){
        this.personalCode = personalCode;
        return this;
    }

}
