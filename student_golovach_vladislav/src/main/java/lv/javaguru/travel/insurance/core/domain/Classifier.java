package lv.javaguru.travel.insurance.core.domain;
import lombok.*;
import org.springframework.lang.NonNull;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "classifiers")
public class Classifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Long id;
    @Column (name="title", nullable = false)
    private String title;
    @Column (name="description", nullable = false)
    private String description;
}
