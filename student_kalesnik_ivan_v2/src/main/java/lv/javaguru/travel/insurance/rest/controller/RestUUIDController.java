package lv.javaguru.travel.insurance.rest.controller;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.TravelCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCoreResult;
import lv.javaguru.travel.insurance.core.services.cute.cuteStarterImpl;
import lv.javaguru.travel.insurance.dto.controller.DtoController;
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
    private cuteStarterImpl cuteStarter;

    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger loggerTime;

    @Autowired
    private DtoController DtoController;

    @GetMapping(path = "/{uuid}",
            produces = "application/json")
    public TravelAgreementResponseGetter getAgreement(@PathVariable String uuid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelAgreementResponseGetter response = processRequest(uuid);
        loggerTime.logExecutionTime(stopwatch);
        return response;
    }

    private TravelAgreementResponseGetter processRequest(String uuid) {
        requestLogger.log(uuid);
        TravelCoreCommand coreCommand = DtoController.buildCoreCommand(uuid);
        TravelCoreResult coreResult = cuteStarter.getAgreement(coreCommand);
        return DtoController.buildResponse(coreResult);
    }
}
