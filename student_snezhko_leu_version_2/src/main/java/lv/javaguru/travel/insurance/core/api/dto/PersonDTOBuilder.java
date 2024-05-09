package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PersonDTOBuilder {
    private String personFirstName;
    private String personLastName;

    private UUID personIc;
    private Date birthDate;
    private BigDecimal personPremium;
    private String medicalRiskLimitLevel;
    private List<RiskDTO> selectedRisks = new ArrayList<>();

    public static PersonDTOBuilder createPersonDTO() {
        return new PersonDTOBuilder();
    }
    public PersonDTO build() {
        return new PersonDTO(personFirstName, personLastName, personIc, medicalRiskLimitLevel, birthDate, personPremium, selectedRisks);
    }

    public PersonDTOBuilder withFirstName(String firstName) {
        this.personFirstName = firstName;
        return this;
    }

    public PersonDTOBuilder withLastName(String lastName) {
        this.personLastName = lastName;
        return this;
    }

    public PersonDTOBuilder withIc(UUID ic) {
        this.personIc = ic;
        return this;
    }
    public PersonDTOBuilder withMedicalRiskLimitLevel(String medicalRiskLimitLevel) {
        this.medicalRiskLimitLevel = medicalRiskLimitLevel;
        return this;
    }

    public PersonDTOBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonDTOBuilder withPersonPremium(BigDecimal premium) {
        this.personPremium = premium;
        return this;
    }

    public PersonDTOBuilder withSelectedRisks(RiskDTO risk) {
        this.selectedRisks.add(risk);
        return this;
    }

    public PersonDTOBuilder withPersonRisk(RiskDTOBuilder risk) {
        this.selectedRisks.add(risk.build());
        return this;
    }
}
