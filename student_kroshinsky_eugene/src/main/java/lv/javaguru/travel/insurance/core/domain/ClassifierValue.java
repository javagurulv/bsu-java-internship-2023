package lv.javaguru.travel.insurance.core.domain;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="classifier_values")
public class ClassifierValue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="classifier_id", nullable=false)
    private Classifier classifier;

    @Column(name = "ic", unique=true, length = 200,nullable=false)
    private String ic;

    @Column(name = "description", length = 500, nullable=false)
    private String description;
}
