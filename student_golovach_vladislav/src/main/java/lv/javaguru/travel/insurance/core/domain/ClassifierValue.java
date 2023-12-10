package lv.javaguru.travel.insurance.core.domain;
import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "classifier_values")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassifierValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Column(name = "classifier_id", nullable = false)
    private Classifier classifier;
    @Column(name = "ic", nullable = false)
    private String ic;
    @Column(name = "description", nullable = false)
    private String description;
}
