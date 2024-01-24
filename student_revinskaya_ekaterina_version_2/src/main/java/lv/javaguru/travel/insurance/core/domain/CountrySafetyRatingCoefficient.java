package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="country_safety_rating_coefficient")
@Setter
@Getter
public class CountrySafetyRatingCoefficient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="country_ic", nullable = false)
    private String countryIc;
    @Column(name= "rating", precision = 2, nullable = false)
    private BigDecimal rating;

}
