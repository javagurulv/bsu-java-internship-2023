package lv.javaguru.travel.insurance.core.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="medical_risk_limit_level")
public class LimitLevel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_risk_limit_level_ic", unique=true, nullable=false)
    private String ic;

    @Column(name = "coefficient", nullable=false)
    private BigDecimal coefficient;
}
