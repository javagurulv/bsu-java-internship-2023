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
@Table(name="age_coefficient")
public class AgeCoefficient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age_from", unique=true, nullable=false)
    private int ageFrom;

    @Column(name = "age_to", unique=true, nullable=false)
    private int ageTo;

    @Column(name = "coefficient", nullable=false)
    private BigDecimal coefficient;
}
