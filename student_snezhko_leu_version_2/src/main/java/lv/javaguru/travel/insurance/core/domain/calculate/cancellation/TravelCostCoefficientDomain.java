package lv.javaguru.travel.insurance.core.domain.calculate.cancellation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "travel_cost_coefficient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCostCoefficientDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost_from", precision = 10, scale = 2, nullable = false)
    private BigDecimal costFrom;

    @Column(name = "cost_to", precision = 10, scale = 2, nullable = false)
    private BigDecimal costTo;


    @Column(name = "cost_coefficient", nullable = false, precision = 10, scale = 2)
    private BigDecimal coefficient;
}
