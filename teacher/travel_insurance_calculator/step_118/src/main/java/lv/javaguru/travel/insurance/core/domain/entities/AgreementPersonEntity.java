package lv.javaguru.travel.insurance.core.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "agreement_persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementPersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agreement_id", nullable = false)
    private AgreementEntity agreement;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @Column(name = "medical_risk_limit_level", nullable = false)
    private String medicalRiskLimitLevel;

}
