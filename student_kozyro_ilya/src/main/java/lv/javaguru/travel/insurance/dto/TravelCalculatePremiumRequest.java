package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lv.javaguru.travel.insurance.format.AppDatePattern;

import java.util.Date;


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

}
