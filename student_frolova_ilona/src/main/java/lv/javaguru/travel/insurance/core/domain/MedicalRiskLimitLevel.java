package lv.javaguru.travel.insurance.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_risk_limit_level")
public class MedicalRiskLimitLevel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEDICAL_RISK_LIMIT_LEVEL_IC", nullable = false)
    private String medicalRiskLimitLevelIc;

    @Column(name = "COEFFICIENT", nullable = false)
    private BigDecimal coefficient;
}
