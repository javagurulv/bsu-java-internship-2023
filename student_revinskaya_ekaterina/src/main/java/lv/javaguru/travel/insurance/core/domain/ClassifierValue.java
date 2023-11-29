package lv.javaguru.travel.insurance.core.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "classifier_values")
@Getter
@Setter
public class ClassifierValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "classifier_id", nullable = false)
    private Classifier classifier;
    @Column(name = "ic", nullable = false)
    private String ic;
    @Column(name = "description", nullable = false)
    private String description;
}
