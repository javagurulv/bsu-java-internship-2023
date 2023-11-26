package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medical_risk_limit_level")
@Getter
@Setter
public class MedicalRiskLimitLevel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medical_risk_limit_level_ic", nullable = false)
    private String medicalRiskLimitLevelIc;
    @Column(name = "coefficient", nullable = false)
    private BigDecimal coefficient;
}
