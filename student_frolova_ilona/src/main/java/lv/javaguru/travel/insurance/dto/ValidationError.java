package lv.javaguru.travel.insurance.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ValidationError {
    private String errorCode;
    private String description;
}
