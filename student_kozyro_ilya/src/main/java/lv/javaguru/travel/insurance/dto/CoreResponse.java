package lv.javaguru.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.List;

@NoArgsConstructor
@Data
public class CoreResponse {
    protected List<ValidationError> errorList;

    public boolean hasErrors() {
        return errorList != null && !errorList.isEmpty();
    }
    CoreResponse(List<ValidationError> errorList) {
        this.errorList = errorList;
    }
}
