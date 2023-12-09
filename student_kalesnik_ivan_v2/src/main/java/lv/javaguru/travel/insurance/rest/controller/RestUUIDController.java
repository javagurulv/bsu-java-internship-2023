package lv.javaguru.travel.insurance.rest.controller;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.dto.controller.TravelAgreementResponseGetter;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/insurance/travel/api/internal")
public class RestUUIDController {

    @Autowired
    private UUIDRequest requestLogger;

    @Autowired
    private UUIDResponse responseLogger;

    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger loggerTime;

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public TravelAgreementResponseGetter getAgreement(@PathVariable String uuid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelAgreementResponseGetter response = processRequest(uuid);
        new TravelAgreementResponseGetter().builder()
                .agreementDateFrom(new Date())
                .agreementDateTo(new Date())
                .uuid(uuid)
                .build();
        loggerTime.logExecutionTime(stopwatch);
        return response;
    }

    private TravelAgreementResponseGetter processRequest(String uuid) {
        requestLogger.log(uuid);
        TravelAgreementResponseGetter response = new TravelAgreementResponseGetter().builder()
                .agreementDateFrom(new Date())
                .agreementDateTo(new Date())
                .uuid(uuid)
                .build();
        responseLogger.log(response);
        return response;
    }
}
