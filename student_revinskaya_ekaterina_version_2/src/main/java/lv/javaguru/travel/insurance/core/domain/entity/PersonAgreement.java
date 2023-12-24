package lv.javaguru.travel.insurance.core.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "person_agreements")
@Getter
@Setter
public class PersonAgreement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personId;
    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreementId;
    @Column(name = "medical_risk_limit_level")
    private String medicalRiskLimitLevel;
}
