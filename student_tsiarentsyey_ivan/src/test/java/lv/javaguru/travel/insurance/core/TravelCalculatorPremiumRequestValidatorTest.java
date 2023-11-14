package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TravelCalculatorPremiumRequestValidatorTest {

    @Autowired
    TravelCalculatorPremiumRequestValidator validator;

    TravelCalculatePremiumRequest requestWrong = new TravelCalculatePremiumRequest("", "last", new Date(5000), new Date(100000));
    TravelCalculatePremiumRequest requestOk = new TravelCalculatePremiumRequest("name", "last", new Date(5000), new Date(100000));


    @Test
    void validateTest() {
        assertThat(!validator.validate(requestWrong).isEmpty()).isEqualTo(true);
        assertThat(!validator.validate(requestOk).isEmpty()).isEqualTo(false);
    }




}
