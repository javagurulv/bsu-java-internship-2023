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

    public String getDescription(String errorCode) {

        return environment.getProperty(errorCode);
    }
    public String getErrorDescription(String errorCode, List<Placeholder> placeholders){
        String errorDescription = environment.getProperty(errorCode);
        for (Placeholder placeholder: placeholders){
            errorDescription = errorDescription.replace("{"+placeholder.getPlaceholderName()+"}", placeholder.getPlaceholderValue());
        }
        return errorDescription;
    }


}