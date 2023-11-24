package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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