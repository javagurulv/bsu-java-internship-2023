package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoreResponse {
    protected List<ValidationError> errorList;
}
