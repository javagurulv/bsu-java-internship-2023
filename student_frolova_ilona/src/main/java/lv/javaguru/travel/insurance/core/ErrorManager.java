package lv.javaguru.travel.insurance.core;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class ErrorManager {

    private final Properties properties;

    ErrorManager() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        properties = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getErrorDescription(String errorCode) {
        return properties.getProperty(errorCode);
    }

}
