package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgreementDTOBuilder {

    private Date agreementDateFrom;
    private Date agreementDateTo;
    private String country;
    private List<String> selectedRisks = new ArrayList<>();
    private List<PersonDTO> persons = new ArrayList<>();
    private BigDecimal agreementPremium;

    public static AgreementDTOBuilder createAgreement() {
        return new AgreementDTOBuilder();
    }

    public AgreementDTO build() {
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setAgreementDateFrom(agreementDateFrom);
        agreementDTO.setAgreementDateTo(agreementDateTo);
        agreementDTO.setCountry(country);
        agreementDTO.setSelectedRisks(selectedRisks);
        agreementDTO.setPersons(persons);
        agreementDTO.setAgreementPremium(agreementPremium);
        return agreementDTO;
    }

    public AgreementDTOBuilder withDateFrom(Date agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
        return this;
    }

    public AgreementDTOBuilder withDateTo(Date agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
        return this;
    }

    public AgreementDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AgreementDTOBuilder withPremium(BigDecimal agreementPremium) {
        this.agreementPremium = agreementPremium;
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

}
