package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {
    BigDecimal agreementPrice;
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;
    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
