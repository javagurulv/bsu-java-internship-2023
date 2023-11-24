package lv.javaguru.travel.insurance.core;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

}
