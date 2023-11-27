package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lv.javaguru.travel.insurance.format.AppDatePattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;

    private String personLastName;

    @JsonFormat(pattern = AppDatePattern.datePattern)
    private Date agreementDateFrom;

    @JsonFormat(pattern = AppDatePattern.datePattern)
    private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
}
