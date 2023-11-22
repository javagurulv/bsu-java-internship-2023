package lv.javaguru.travel.insurance.core.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "classifier_values")
public class ClassifierValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "classifier_id", nullable = false)
    private Classifier classifier_id;
    @Column(name = "ic", nullable = false)
    String ic;
    @Column(name = "description", nullable = false)
    String description;
}
