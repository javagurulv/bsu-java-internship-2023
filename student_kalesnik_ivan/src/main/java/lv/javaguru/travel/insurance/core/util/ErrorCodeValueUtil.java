package lv.javaguru.travel.insurance.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Component
@PropertySource("classpath:errorCodes.properties")
public class ErrorCodeValueUtil {
    private Properties props;
    ErrorCodeValueUtil() throws IOException {
        Resource resource = new ClassPathResource("errorCodes.properties");
        props = PropertiesLoaderUtils.loadProperties(resource);
    }

    public String getErrorDescription(String errorCode) {
        return props.getProperty(errorCode);
    }
}
