package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "first_name", nullable = false)
    private String firstName;
    @JoinColumn(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "personal_code", nullable = false)
    private String personalCode;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
}
