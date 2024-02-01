package lv.javaguru.travel.insurance.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationError {

    private String errorCode;

    private String description;

}
