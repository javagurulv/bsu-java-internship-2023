package lv.javaguru.travel.insurance.dto.v1;

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

public class TravelCalculatePremiumRequestV1 {
    @JsonAlias("travel_cost")
    private BigDecimal travelCost;
    @JsonAlias("person_first_name")
    private String personFirstName;
    @JsonAlias("person_last_name")
    private String personLastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_from")
    private Date agreementDateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonAlias("agreement_date_to")
    private Date agreementDateTo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @JsonAlias("personal_code")
    private String personalCode;
    @JsonAlias("selected_risks")
    private List<String> selectedRisks;
    private String country;
    @JsonAlias("medical_risk_limit_level")
    private String medicalRiskLimitLevel;
}
