package lv.javaguru.travel.insurance.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class ErrorFileLoaderUtil {
    private final Properties properties;

    ErrorFileLoaderUtil(ResourceLoader resourceLoader, @Value("${errorCodeFile.fileName}") String fileName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:".concat(fileName));
        properties = new Properties();
        properties.load(resource.getInputStream());
    }

    public String getErrorDescription(String code) {
        return properties.getProperty(code);
    }
}
