package lv.javaguru.travel.insurance.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COUNTRY_DEFAULT_DAY_RATE")
public class CountryDefaultDayRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "country_ic", nullable = false)
    private String countryIc;


    @Column(name = "country_default_day_rate", precision = 10, scale = 2, nullable = false)
    private BigDecimal countryDefaultDayRateCoefficient;
}
