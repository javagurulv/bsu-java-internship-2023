package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;

@Component
public class TravelRequestMedicalRiskLimitLevelNotEmptyValidation extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        Optional<ValidationError> result = Optional.empty();
        try {
            String property = "medical.risk.limit.level.enabled";
            if (checkProperty(property)) {
                if (request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty())
                {
                    String errorCode = "ERROR_CODE_14";
                    result = Optional.of(errorFactory.buildError(errorCode));
                }
            }
        }
        catch (IOException e) {

        }
        return result;
    }

    /*
    private boolean checkMedicalRiskLimitLevelProperty() throws IOException {
        Properties property = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
        String value = property.getProperty("medical.risk.limit.level.enabled");
        return "true".equals(value);
    }

     */
}
