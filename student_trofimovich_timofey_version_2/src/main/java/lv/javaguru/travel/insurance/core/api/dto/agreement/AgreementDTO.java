package lv.javaguru.travel.insurance.core.api.dto.agreement;

import lombok.*;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class AgreementDTO {

    @XmlElement(name = "dateFrom")
    private Date agreementDateFrom;

    @XmlElement(name = "dateTo")
    private Date agreementDateTo;

    @XmlElement(name = "country")
    private String country;

    @XmlElementWrapper(name="risks")
    @XmlElement(name = "risk")
    private List<String> selectedRisks;

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<PersonDTO> persons;

    @XmlElement(name = "premium")
    private BigDecimal agreementPremium;

    @XmlElement(name = "uuid")
    private String uuid;
}
