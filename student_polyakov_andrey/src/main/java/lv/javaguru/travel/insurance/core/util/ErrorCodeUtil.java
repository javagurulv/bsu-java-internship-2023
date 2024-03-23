package lv.javaguru.travel.insurance.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class ErrorCodeUtil {
    private Properties properties;

    ErrorCodeUtil() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        properties = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getErrorDescription(String errorCode) {
        return properties.getProperty(errorCode);
    }
    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = properties.getProperty(errorCode);
        for (Placeholder placeholder: placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceholderName() + "}";
            errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceholderValue());
        }
        return errorDescription;
    }
}
