package lv.javaguru.travel.insurance.core.api.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumRequestV2 {

    @JsonAlias("agreement_date_to")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    @JsonAlias("agreement_date_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonAlias("selected_risks")
    private List<String> selectedRisks;

    private String country;

    private List<PersonRequestV2DTO> persons;

}
