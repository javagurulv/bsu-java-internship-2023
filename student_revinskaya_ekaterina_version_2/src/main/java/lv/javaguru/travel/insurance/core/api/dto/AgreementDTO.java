package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgreementDTO {
    private String uuid;

    private BigDecimal travelCost;

    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;

}
