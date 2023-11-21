package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:errorCodes.properties")
public class  ErrorCodesPropertiesReader{

    @Autowired
    private Environment environment;

    public String getDescription(String errorCode) {

        return environment.getProperty(errorCode);
    }

}