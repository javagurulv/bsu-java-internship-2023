package lv.javaguru.travel.insurance.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@PropertySource("classpath:errorCodes.properties")
public class ErrorCodeUtil {

    @Autowired
    private Environment environment;

    public String getErrorDescription(String errorCode) {

        return environment.getProperty(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = environment.getProperty(errorCode);
        if (errorDescription != null) {
            for (Placeholder placeholder : placeholders) {
                String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
                errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceholderValue());
            }
        }
        return errorDescription;
    }


}