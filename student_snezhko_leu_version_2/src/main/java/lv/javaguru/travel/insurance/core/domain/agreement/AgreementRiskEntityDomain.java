package lv.javaguru.travel.insurance.core.domain.agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "selected_risks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementRiskEntityDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "risk_ic", nullable = false)
    private String riskIc;


    @ManyToOne
    @JoinColumn(name = "agreement", nullable = false)
    private AgreementEntityDomain agreement;
}
