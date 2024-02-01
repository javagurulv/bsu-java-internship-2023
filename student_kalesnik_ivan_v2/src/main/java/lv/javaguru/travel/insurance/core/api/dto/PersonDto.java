package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder(access = AccessLevel.PUBLIC)
public class PersonDto {
    private String personFirstName;

    private String personLastName;

    private String personCode;

    private Date personBirthDate;

    private BigDecimal travelCost;

    private String medicalRiskLimitLevel;

    private List<RiskDto> risks;

}
