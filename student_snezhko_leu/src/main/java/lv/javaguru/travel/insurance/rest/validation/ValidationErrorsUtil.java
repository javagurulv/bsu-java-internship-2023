package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class ValidationErrorsUtil {
    private Properties property;
    public ValidationErrorsUtil() throws IOException {
        ClassPathResource resource = new ClassPathResource("errorCodes.properties");
        property = PropertiesLoaderUtils.loadProperties(resource);
    }
    public String getDescriptionByErrorCode(String errorCode) {
        return property.getProperty(errorCode);
    }
}
