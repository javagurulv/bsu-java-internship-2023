package lv.javaguru.travel.insurance.rest.internal;

import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance/travel/api/internal/agreement/{uuid}")
class TravelGetPremiumController {
    private static TravelCalculatePremiumResponseV2 responseV2 = new TravelCalculatePremiumResponseV2();

    @GetMapping(path = "/",
            produces = "application/json")
    public TravelCalculatePremiumResponseV2 getPolicy(@PathVariable String uuid) {
        responseV2.setUuid(uuid);
        return responseV2;
    }
}