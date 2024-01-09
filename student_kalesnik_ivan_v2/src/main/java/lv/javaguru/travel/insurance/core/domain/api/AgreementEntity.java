package lv.javaguru.travel.insurance.core.domain.api;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "agreements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgreementEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "date_from", nullable = false)
    private Date agreementDateFrom;

    @Column(name = "date_to", nullable = false)
    private Date agreementDateTo;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "premium", nullable = false)
    private BigDecimal agreementPremium;
    @Column(name = "uuid", nullable = false)
    private String uuid;

}

