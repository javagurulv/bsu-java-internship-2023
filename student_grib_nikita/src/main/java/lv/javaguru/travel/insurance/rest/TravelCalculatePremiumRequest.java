package lv.javaguru.travel.insurance.rest;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TravelCalculatePremiumRequest {
    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
}
