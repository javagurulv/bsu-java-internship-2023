package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;

}
