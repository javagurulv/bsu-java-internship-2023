package lv.javaguru.travel.insurance.core.api.dto.person;

import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;

import java.util.Date;
import java.util.List;

@Setter
public class PersonDTOBuilder {

    private String personFirstName;
    private String personLastName;
    private Date personBirthDate;
    private List<RiskDTO> selectedRisks;
    private String medicalRiskLimitLevel;

    public PersonDTO build() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonFirstName(personFirstName);
        personDTO.setPersonLastName(personLastName);
        personDTO.setPersonBirthDate(personBirthDate);
        personDTO.setSelectedRisks(selectedRisks);
        personDTO.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
        return personDTO;
    }

    public static PersonDTOBuilder createPerson() {
        return new PersonDTOBuilder();
    }

    public PersonDTOBuilder withPersonFirstName(String personFirstName) {
        this.setPersonFirstName(personFirstName);
        return this;
    }
    public PersonDTOBuilder withPersonLastName(String personLastName) {
        this.setPersonLastName(personLastName);
        return this;
    }
    public PersonDTOBuilder withPersonBirthDate(Date personBirthDate) {
        this.setPersonBirthDate(personBirthDate);
        return this;
    }

    public PersonDTOBuilder withSelectedRisk(RiskDTO risk) {
        this.selectedRisks.add(risk);
        return this;
    }
    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.setMedicalRiskLimitLevel(medicalRiskLimitLevel);
        return this;
    }
}
