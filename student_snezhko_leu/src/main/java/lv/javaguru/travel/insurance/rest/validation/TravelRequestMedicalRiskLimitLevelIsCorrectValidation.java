package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class TravelRequestMedicalRiskLimitLevelIsCorrectValidation extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Autowired
    private ClassifierValueRepository cvRepository;

    List<Placeholder> placeholders;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Optional<ValidationError> result = Optional.empty();
        if (request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty()) {
            return result;
        }
        try {
            Optional<ClassifierValue> cv = cvRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel());
            if (checkEnabledProperty() && cv.isEmpty()) {
                String errorCode = "ERROR_CODE_15";
                result = Optional.of(
                        errorFactory.buildError(
                                errorCode,
                                initPlaceholders(
                                        placeholders,
                                        request.getMedicalRiskLimitLevel()
                                )
                        )
                );
            }
        }
        catch (IOException e) {

        }
        return result;
    }

    private boolean checkEnabledProperty() throws IOException {
        Properties property = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
        String value = property.getProperty("medical.risk.limit.level.enabled");
        return "true".equals(value);
    }
    private List<Placeholder> initPlaceholders(List<Placeholder> result, String incorrectLimit) {
        Placeholder placeholder = new Placeholder("{NOT_EXISTING_LEVEL}", incorrectLimit);
        result.add(placeholder);
        return result;
    }
}
