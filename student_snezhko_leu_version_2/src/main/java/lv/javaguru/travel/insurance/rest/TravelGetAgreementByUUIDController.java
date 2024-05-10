package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement")
public class TravelGetAgreementByUUIDController {
    @GetMapping(path = "/{uuid}",
                consumes = "application/json",
                produces = "application/json")
    public AgreementDTO getAgreement(@PathVariable("uuid") UUID uuid) {
        return new AgreementDTO(Date.valueOf("2050-02-02"),
                Date.valueOf("2050-02-05"),
                uuid,
                "COUNTRY",
                BigDecimal.ONE,
                List.of("TRAVEL_EVACUATION"),
                List.of());
    }
}
