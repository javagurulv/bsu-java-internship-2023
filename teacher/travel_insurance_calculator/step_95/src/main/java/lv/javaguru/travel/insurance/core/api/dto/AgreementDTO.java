package lv.javaguru.travel.insurance.core.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDTO {

    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private String medicalRiskLimitLevel;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;

}
