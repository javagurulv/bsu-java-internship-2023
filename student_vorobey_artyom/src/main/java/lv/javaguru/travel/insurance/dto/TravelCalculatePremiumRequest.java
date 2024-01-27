package lv.javaguru.travel.insurance.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    private String[] selectedRisks;
}
