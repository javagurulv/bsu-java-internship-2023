package lv.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lv.javaguru.travel.insurance.core.utils.BigDecimalSerializer;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelRisk {
    String riskIc;

    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal premium;
}
