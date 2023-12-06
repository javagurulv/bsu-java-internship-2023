package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class ValidationErrorDto {
    private String errorCode;

    private String description;
}
