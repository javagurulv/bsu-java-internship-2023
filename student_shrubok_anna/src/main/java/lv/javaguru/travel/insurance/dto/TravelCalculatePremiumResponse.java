package lv.javaguru.travel.insurance.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  private Date agreementDateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")  private Date agreementDateTo;

    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

}
