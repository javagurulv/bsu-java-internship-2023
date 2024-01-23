package lv.javaguru.travel.insurance.core.util;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.placeholder.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class ValidationErrorsUtil {
    /*@Autowired
    Environment env;
    */
    private Properties property;

    public ValidationErrorsUtil() throws IOException {
        ClassPathResource resource = new ClassPathResource("ErrorCodes.properties");
        property = PropertiesLoaderUtils.loadProperties(resource);


    }
    public String getDescriptionByErrorCode(String errorCode) {
       /* Environment env = new StandardServletEnvironment();
        return env.getProperty(errorCode);

        */
        return property.getProperty(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String description = getDescriptionByErrorCode(errorCode);
        for (Placeholder placeholder : placeholders) {
            description = description.replace("{" + placeholder.getPlaceholderName() + "}", placeholder.getPlaceholderValue());
        }
        return description;
    }
}
