package lv.javaguru.travel.insurance.web.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.calculate.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.v2.ConverterV2DTO;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.core.api.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.core.services.calculate.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceWebControllerV2 {
    @Autowired
    private TravelCalculatePremiumService service;

    @Autowired
    private ConverterV2DTO converterV2DTO;
    @Autowired
    private TravelCalculatePremiumRequestExecutionTimeLogger timeLogger;
    /*
    .NullPointerException:
    Cannot invoke "java.util.List.stream()"
    because the return value of
    "lv.javaguru.travel.insurance.core.api.dto.v2.
        PersonRequestV2DTO.getSelectedRisks()" is null
     */
    @GetMapping("insurance/travel/web/v2")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV2());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV2());

        return "travel-calculate-premium-v2";
    }

    @PostMapping("insurance/travel/web/v2")
    public String processForm(@ModelAttribute("request")TravelCalculatePremiumRequestV2 request,
                              ModelMap modelMap) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumCoreCommand command = converterV2DTO.buildCommand(request);
        TravelCalculatePremiumCoreResult result = service.calculatePremium(command);
        TravelCalculatePremiumResponseV2 response = converterV2DTO.buildResponse(result);

        stopwatch.stop();
        timeLogger.log(stopwatch);

        modelMap.addAttribute("request", request);
        modelMap.addAttribute("response", response);

        return "travel-calculate-premium-v2";
    }
}
