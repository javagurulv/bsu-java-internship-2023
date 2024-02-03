package lv.javaguru.travel.insurance.core.api.dto.risk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class RiskDTO {

    @XmlElement(name = "ic")
    private String riskIc;

    @XmlElement(name = "premium")
    private BigDecimal premium;
}
