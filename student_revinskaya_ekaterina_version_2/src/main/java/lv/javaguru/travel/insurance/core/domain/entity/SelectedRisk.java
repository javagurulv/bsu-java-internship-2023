package lv.javaguru.travel.insurance.core.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "selected_risks")
@Getter
@Setter
public class SelectedRisk {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreementId;
    @Column(name = "risk_ic", nullable = false)
    private String riskIc;

}
