package lv.javaguru.travel.insurance.core.domain.agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "agreement_persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementPersonEntityDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person", nullable = false)
    private PersonDTODomain person;

    @ManyToOne
    @JoinColumn(name = "agreement", nullable = false)
    private AgreementEntityDomain agreement;

    @Column(name = "medical_risk_limit_level")
    private String medicalRiskLimitLevel;


    @Column(name = "premium",
           precision = 10,
            scale = 2,
            nullable = false)
    private BigDecimal premium;
}
