package lv.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TravelCalculatePremiumRequestV2 {
    @JsonAlias("travel_cost")
    private BigDecimal travelCost;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_from")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_to")
    private Date agreementDateTo;
    @JsonAlias("selected_risks")
    private List<String> selectedRisks;
    private String country;
    List<PersonRequest> persons;
}
