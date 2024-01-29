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
@Table(name = "MEDICAL_RISK_LIMIT_LEVEL")
public class MedicalRiskLimitLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "MEDICAL_RISK_LIMIT_LEVEL_IC", nullable = false)
    private String medicalRiskLimitLevelIc;

    @Column(name = "COEFFICIENT", nullable = false)
    private BigDecimal coefficient;
}
