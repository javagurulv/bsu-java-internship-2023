package lv.javaguru.travel.insurance.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TravelRisk {
    String riskIc;
    BigDecimal premium;
}
