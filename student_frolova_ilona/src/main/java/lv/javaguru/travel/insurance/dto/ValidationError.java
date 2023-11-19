package lv.javaguru.travel.insurance.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ValidationError {
    private String field;
    private String message;
}
