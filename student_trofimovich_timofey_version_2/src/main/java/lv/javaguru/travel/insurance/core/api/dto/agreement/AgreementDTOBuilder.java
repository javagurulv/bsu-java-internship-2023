package lv.javaguru.travel.insurance.core.api.dto.agreement;


import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTOBuilder;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
public class AgreementDTOBuilder {
    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private List<String> selectedRisks = new ArrayList<>();

    private List<PersonDTO> persons = new ArrayList<>();

    private BigDecimal agreementPremium;

    private String uuid;

    public static AgreementDTOBuilder createAgreement() {
        return new AgreementDTOBuilder();
    }
    public AgreementDTOBuilder withAgreementDateFrom(Date agreementDateFrom) {
        this.setAgreementDateFrom(agreementDateFrom);
        return this;
    }
    public AgreementDTOBuilder withAgreementDateTo(Date agreementDateTo) {
        this.setAgreementDateTo(agreementDateTo);
        return this;
    }
    public AgreementDTOBuilder withUUID(String uuid) {
        this.setUuid(uuid);
        return this;
    }
    public AgreementDTOBuilder withCountry(String country) {
        this.setCountry(country);
        return this;
    }
    public AgreementDTOBuilder withSelectedRisk(String selectedRisk) {
        this.selectedRisks.add(selectedRisk);
        return this;
    }
    public AgreementDTOBuilder withPerson(PersonDTO person) {
        this.persons.add(person);
        return this;
    }

    public AgreementDTOBuilder withPerson(PersonDTOBuilder personDTOBuilder) {
        this.persons.add(personDTOBuilder.build());
        return this;
    }
    public AgreementDTOBuilder withAgreementPremium(BigDecimal premium) {
        this.setAgreementPremium(premium);
        return this;
    }

    public AgreementDTO build() {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementDateFrom);
        agreementDTO.setAgreementDateTo(agreementDateTo);
        agreementDTO.setCountry(country);
        agreementDTO.setSelectedRisks(selectedRisks);
        agreementDTO.setPersons(persons);
        agreementDTO.setAgreementPremium(agreementPremium);
        agreementDTO.setUuid(uuid);
        return agreementDTO;
    }
}
