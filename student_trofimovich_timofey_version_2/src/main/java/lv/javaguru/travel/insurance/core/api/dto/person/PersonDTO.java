package lv.javaguru.travel.insurance.core.api.dto.person;

import lombok.*;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDTO {

    @XmlElement(name = "person_code")
    private String personUUID;

    @XmlElement(name = "firstName")
    private String personFirstName;

    @XmlElement(name = "lastName")
    private String personLastName;

    @XmlElement(name = "birthDate")
    private Date personBirthDate;

    @XmlElementWrapper(name = "risks")
    @XmlElement(name = "risk")
    private List<RiskDTO> selectedRisks;

    @XmlElement(name = "medicalRiskLimitLevel")
    private String medicalRiskLimitLevel;

    @XmlElement(name = "travelCost")
    private BigDecimal travelCost;
}
