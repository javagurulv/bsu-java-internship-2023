package lv.javaguru.travel.insurance.rest;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;

}
