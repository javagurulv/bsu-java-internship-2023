package lv.javaguru.travel.insurance.core.domain.entity;

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
    @JoinColumn(name = "person_agreement_id", nullable = false)
    private PersonAgreement personAgreementId;
    @Column(name = "risk_ic", nullable = false)
    private String riskIc;
    @Column(name = "premium", nullable = false)
    private BigDecimal premium;
}
