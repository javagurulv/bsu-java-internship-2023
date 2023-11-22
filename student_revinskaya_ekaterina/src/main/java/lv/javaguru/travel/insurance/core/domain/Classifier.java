package lv.javaguru.travel.insurance.core.domain;

import javax.persistence.*;
import java.math.BigInteger;
@Entity
@Table(name = "classifiers")
public class Classifier {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
}
