package lv.javaguru.travel.insurance.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "agreement")
@XmlAccessorType(XmlAccessType.NONE)
public class AgreementEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id")
    private Long id;

    @Column(name = "date_from", nullable = false)
    @XmlElement(name = "dateFrom")
    private Date agreementDateFrom;

    @Column(name = "date_to", nullable = false)
    @XmlElement(name = "dateTo")
    private Date agreementDateTo;


    @Column(name = "country", nullable = false)
    @XmlElement(name = "country")
    private String country;

    @Column(name = "premium", nullable = false)
    @XmlElement(name = "agreementPremium")
    private BigDecimal agreementPremium;

    @Column(name = "uuid", nullable = false)
    @XmlElement(name = "uuid")
    private String uuid;
}
