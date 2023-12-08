package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lv.javaguru.travel.insurance.format.AppDatePattern;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumRequest extends CoreRequest{

    private String personFirstName;
    private String personLastName;

    @JsonFormat(pattern = AppDatePattern.datePattern)
    private Date agreementDateFrom;

    @JsonFormat(pattern = AppDatePattern.datePattern)
    private Date agreementDateTo;

    @JsonProperty("selected_risks")
    private List<String> selectedRisks;

}
