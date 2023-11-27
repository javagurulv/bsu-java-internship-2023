package lv.javaguru.travel.insurance.web;

import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TravelInsuranceController {

    @Autowired private TravelCalculatePremiumService service;

    @GetMapping("/insurance/travel/web")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV1());
        return "travel-calculate-premium";
    }

    @PostMapping("/insurance/travel/web")
    public String processForm(@ModelAttribute(value = "request") @Validated TravelCalculatePremiumRequestV1 request,
                              BindingResult bindingResult,
                              ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "travel-calculate-premium";
        }
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);
        modelMap.addAttribute("response", response);

        return "travel-calculate-premium";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
