package lv.javaguru.travel.insurance.dto;

import java.util.Date;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;


}
