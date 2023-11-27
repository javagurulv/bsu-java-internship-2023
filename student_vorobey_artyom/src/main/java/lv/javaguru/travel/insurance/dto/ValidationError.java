package lv.javaguru.travel.insurance.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ValidationError {
    private String field;
    private String message;
}
