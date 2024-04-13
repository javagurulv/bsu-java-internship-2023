package lv.javaguru.travel.insurance.core.domain.agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgreementEntityDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "premium", precision = 10, scale = 2, nullable = false)
    private BigDecimal premium;
}
