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
@Table(name="country_default_day_rate")
public class CountryDefaultDayRate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_ic", unique=true, nullable=false)
    private String ic;

    @Column(name = "default_day_rate", nullable=false)
    private BigDecimal dayRate;
}
