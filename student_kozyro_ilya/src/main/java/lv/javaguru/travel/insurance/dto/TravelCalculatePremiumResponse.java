package lv.javaguru.travel.insurance.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;

    private String personLastName;

    private Date agreementDateFrom;

    private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(ArrayList<ValidationError> errors) {
        super(errors);
    }
}
