package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="age_coefficient")
@Setter
@Getter
public class AgeCoefficient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="age_from", nullable = false)
    private int ageFrom;
    @Column(name="age_to", nullable = false)
    private int ageTo;
    @Column(name= "coefficient", precision = 2, nullable = false)
    private BigDecimal coefficient;

}
