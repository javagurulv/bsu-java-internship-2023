package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.api.command.get.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.get.GetAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementByUUIDController {

    @Autowired
    private GetAgreementService service;
    @GetMapping(path = "/{uuid}",
                consumes = "application/json",
                produces = "application/json")
    public TravelGetAgreementCoreResult getAgreement(@PathVariable("uuid") String uuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult result = service.getAgreement(command);
        return result;
        /*
        return new AgreementDTO(Date.valueOf("2050-02-02"),
                Date.valueOf("2050-02-05"),
                uuid,
                "COUNTRY",
                BigDecimal.ONE,
                List.of("TRAVEL_EVACUATION"),
                List.of());

         */
    }
}
