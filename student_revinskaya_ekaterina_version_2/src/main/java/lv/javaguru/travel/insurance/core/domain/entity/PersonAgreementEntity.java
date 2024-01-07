package lv.javaguru.travel.insurance.core.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "person_agreements")
@Getter
@Setter
public class PersonAgreementEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity personEntityId;
    @ManyToOne
    @JoinColumn(name = "agreement_id", nullable = false)
    private AgreementEntity agreementEntityId;
    @Column(name = "medical_risk_limit_level")
    private String medicalRiskLimitLevel;
}
