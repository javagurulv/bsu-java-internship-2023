package lv.javaguru.travel.insurance;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class InsuranceApplication {

    public static void main(String[] args) {
        var logger = LoggerFactory.getLogger(InsuranceApplication.class);
        logger.info("Starting application");
        SpringApplication.run(InsuranceApplication.class, args);
    }

}
