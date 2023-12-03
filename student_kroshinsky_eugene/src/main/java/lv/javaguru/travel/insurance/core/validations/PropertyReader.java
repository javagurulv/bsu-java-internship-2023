package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Component
class PropertyReader {
    Properties properties;
    public PropertyReader() {
        properties = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/errorCode.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}