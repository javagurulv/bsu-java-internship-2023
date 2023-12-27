package lv.javaguru.travel.insurance.core.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ErrorCodeUtil {
    private Properties properties;
    public ErrorCodeUtil() {
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
