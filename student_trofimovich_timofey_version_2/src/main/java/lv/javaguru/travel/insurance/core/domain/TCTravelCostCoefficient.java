package lv.javaguru.travel.insurance.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "travel_cancellation_travel_cost_coefficient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TCTravelCostCoefficient {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "travel_cost_from", precision = 10, scale = 2,
            nullable = false)
    private BigDecimal travelCostFrom;

    @Column(name = "travel_cost_to", precision = 10, scale = 2,
    nullable = false)
    private BigDecimal travelCostTo;

    @Column(name = "coefficient", precision = 10, scale = 2,
    nullable = false)
    private BigDecimal coefficient;

}
