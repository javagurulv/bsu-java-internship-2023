package lv.javaguru.travel.insurance.core.domain.api;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "selected_risks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectedRiskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agreement_id", nullable = false)
    private AgreementEntity agreement;

    @Column(name = "risk_ic", nullable = false)
    private String riskIc;

}
