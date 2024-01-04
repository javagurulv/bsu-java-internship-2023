package lv.javaguru.travel.insurance.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class ErrorCodeUtil {
    private Properties properties;

    public ErrorCodeUtil() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("errorCodes.properties");
        properties = PropertiesLoaderUtils.loadProperties(classPathResource);
    }

    public String getErrorDescription(String errorCode) {
        return properties.getProperty(errorCode);
    }

    public String getErrorDescription(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = properties.getProperty(errorCode);
        for (Placeholder placeholder :
                placeholders) {
            String placeholderToReplace = "{" + placeholder.getPlaceHolderName() + "}";
            errorDescription = errorDescription.replace(placeholderToReplace, placeholder.getPlaceHolderValue());
        }
        return errorDescription;
    }

}
