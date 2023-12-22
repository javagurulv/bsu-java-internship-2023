package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="person_agreement_risks")
@Getter
@Setter
public class PersonAgreementRisk {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_agreement_id")
    private PersonAgreement personAgreementId;
    @Column(name = "risk_ic")
    private String riskIc;
    @Column(name = "premium")
    private BigDecimal premium;
}
