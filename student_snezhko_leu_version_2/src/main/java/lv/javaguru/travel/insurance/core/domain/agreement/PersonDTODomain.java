package lv.javaguru.travel.insurance.core.domain.agreement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTODomain {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String personFirstName;

    @Column(name = "last_name", nullable = false)
    private String personLastName;

    @Column(name = "ic", nullable = false, unique = true)
    private UUID personIc;

    @Column(name = "birth_date", nullable = false)
    private Date personBirthDate;

}
