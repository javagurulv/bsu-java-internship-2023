package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "selected_risks")
@Getter
@Setter
public class SelectedRisk {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne//несколько рисков к одному agreement
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreementId;
    @Column(name = "risk_ic", nullable = false)
    private String riskIc;

}
