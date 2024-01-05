package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="travel_cost_coefficient")
@Setter
@Getter
public class TravelCostCoefficient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cost_from", precision = 2, nullable = false)
    private BigDecimal costFrom;
    @Column(name="cost_to", precision = 2, nullable = false)
    private BigDecimal costTo;
    @Column(name= "coefficient", precision = 2, nullable = false)
    private BigDecimal coefficient;

}
