package lv.javaguru.travel.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class InsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceApplication.class, args);
    }

}
