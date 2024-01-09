package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String personFirstName;

    private String personLastName;

    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    private String personalCode;

    private List<RiskDTO> risks;

}
