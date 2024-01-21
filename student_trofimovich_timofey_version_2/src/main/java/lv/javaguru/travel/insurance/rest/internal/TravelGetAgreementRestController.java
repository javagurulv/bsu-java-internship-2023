package lv.javaguru.travel.insurance.rest.internal;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.services.get.agreement.TravelGetAgreementService;
import lv.javaguru.travel.insurance.dto.internal.DTOGetAgreementConverter;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementRestController {

    @Autowired
    private TravelGetAgreementRequestLogger requestLogger;
    @Autowired
    private TravelGetAgreementResponseLogger responseLogger;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
    @Autowired
    private TravelGetAgreementService service;
    @Autowired
    private DTOGetAgreementConverter converter;

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public TravelGetAgreementResponse getAgreement(@PathVariable String uuid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelGetAgreementResponse response = processRequest(uuid);
        timeLogger.log(stopwatch);
        return response;
    }

    private TravelGetAgreementResponse processRequest(String uuid) {
        requestLogger.log(uuid);
        TravelGetAgreementCoreCommand coreCommand = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult coreResult = service.getAgreement(coreCommand);
        TravelGetAgreementResponse response = converter.buildResponse(coreResult);
        responseLogger.log(response);
        return response;
    }
}
