package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AgreementDTOBuilder {
    private Date agreementDateTo;
    private Date agreementDateFrom;
    private UUID agreementUUID;
    private String country;
    private BigDecimal agreementPremium;
    private List<String> selectedRisks = new ArrayList<>();
    private List<PersonDTO> persons = new ArrayList<>();

    private BigDecimal cost;
    public static AgreementDTOBuilder createAgreementDTO() {
        return new AgreementDTOBuilder();
    }

    public AgreementDTO build() {
        return new AgreementDTO(
                agreementDateFrom,
                agreementDateTo,
                agreementUUID,
                country,
                agreementPremium,
                selectedRisks,
                persons,
                cost
        );
    }

    public AgreementDTOBuilder withDateFrom(Date from) {
        this.agreementDateFrom = from;
        return this;
    }

    public AgreementDTOBuilder withDateTo(Date to) {
        this.agreementDateTo = to;
        return this;
    }

    public AgreementDTOBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AgreementDTOBuilder withPremium(BigDecimal premium) {
        this.agreementPremium = premium;
        return this;
    }
    public AgreementDTOBuilder withSelectedRisks(List<String> risks) {
        this.selectedRisks = risks;
        return this;
    }
    public AgreementDTOBuilder withSelectedRisks(String risk){
        this.selectedRisks.add(risk);
        return this;
    }

    public AgreementDTOBuilder withPersons(PersonDTO person) {
        this.persons.add(person);
        return this;
    }

    public AgreementDTOBuilder withPersons(PersonDTOBuilder personBuilder) {
        this.persons.add(personBuilder.build());
        return this;
    }

    public AgreementDTOBuilder withAgreementUUID(UUID uuid) {
        this.agreementUUID = uuid;
        return this;
    }

    public AgreementDTOBuilder withCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }
}
