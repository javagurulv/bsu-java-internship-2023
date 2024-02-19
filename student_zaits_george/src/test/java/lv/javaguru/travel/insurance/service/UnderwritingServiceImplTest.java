package lv.javaguru.travel.insurance.service;

import lv.javaguru.travel.insurance.model.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.service.impl.UnderwritingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.utils.ConverterTestUtils.buildPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UnderwritingServiceImpl.class)
class UnderwritingServiceImplTest {

    @Autowired
    private UnderwritingService underwritingService;

    @Test
    void test() {
        var request = buildPojo(TravelCalculatePremiumRequest.class);

        var result = underwritingService.calculateAgreementPrice(request);
        var expected = BigDecimal.valueOf(request.getAgreementDateFrom().getTime() - request.getAgreementDateTo().getTime());

        assertEquals(expected, result);
    }

}