package lv.javaguru.travel.insurance.scheduled;

import lombok.Getter;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "agreements")
@XmlAccessorType(XmlAccessType.FIELD)
public class Agreements {


    @Getter
    @Setter
    @XmlElement(name = "agreement")
    private List<AgreementDTO> agreements = new ArrayList<>();

    public void add(AgreementDTO agreement) {
        agreements.add(agreement);
    }

}
