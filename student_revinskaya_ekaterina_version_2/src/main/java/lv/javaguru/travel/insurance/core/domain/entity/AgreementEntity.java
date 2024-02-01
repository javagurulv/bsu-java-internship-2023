package lv.javaguru.travel.insurance.core.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "agreements")
@Getter
@Setter
public class AgreementEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid", nullable = false)
    private String uuid;
    @Column(name = "travel_cost")
    private BigDecimal travelCost;
    @Column(name = "date_from", nullable = false)
    private Date dateFrom;
    @Column(name = "date_to", nullable = false)
    private Date dateTo;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "premium", nullable = false)
    private BigDecimal premium;
}
