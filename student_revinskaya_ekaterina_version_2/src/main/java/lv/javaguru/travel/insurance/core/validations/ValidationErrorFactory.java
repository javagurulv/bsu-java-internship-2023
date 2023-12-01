package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationErrorFactory {
    @Autowired
    private ErrorCodeUtil reader;
    public ValidationErrorDTO buildError(String errCode){
        return new ValidationErrorDTO(errCode, reader.getErrorDescription(errCode));
    }
    public ValidationErrorDTO buildError(String errorCode, List<Placeholder> placeholders){
        return new ValidationErrorDTO(errorCode, reader.getErrorDescription(errorCode, placeholders));
    }

}
