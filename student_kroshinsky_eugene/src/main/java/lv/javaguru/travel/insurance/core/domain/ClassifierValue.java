package lv.javaguru.travel.insurance.core.domain;

import javax.persistence.*;

@Entity
@Table(name="classifiers_values", schema="java-real-practice-insurance")
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

    @Column(name = "descripption", length = 500, nullable=false)
    private String description;

    public ClassifierValue() {
    }

    public ClassifierValue(Long id, Classifier classifier, String ic, String description) {
        this.id = id;
        this.classifier = classifier;
        this.ic = ic;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
