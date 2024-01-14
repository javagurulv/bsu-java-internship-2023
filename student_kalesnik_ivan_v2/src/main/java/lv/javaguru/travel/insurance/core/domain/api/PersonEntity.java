package lv.javaguru.travel.insurance.core.domain.api;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "person_code", nullable = false)
    private String personCode;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

}
