package lv.javaguru.travel.insurance.core.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private String personCode;
    private Date personBirthDate;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> risks = new ArrayList<>();

    public static PersonDTOBuilder createPersonDTO() {
        return new PersonDTOBuilder();
    }

    public PersonDTO build() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(personFirstName);
        personDTO.setPersonLastName(personLastName);
        personDTO.setPersonCode(personCode);
        personDTO.setPersonBirthDate(personBirthDate);
        personDTO.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
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

    public PersonDTOBuilder withPersonCode(String personCode) {
        this.personCode = personCode;
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

}
