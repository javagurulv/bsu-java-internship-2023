package lv.javaguru.travel.insurance.core.api.dto.person;

import lombok.*;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private String personUUID;
    private String personFirstName;
    private String personLastName;
    private Date personBirthDate;
    private List<RiskDTO> selectedRisks;
    private String medicalRiskLimitLevel;
    private BigDecimal travelCost;
}
