package lv.javaguru.travel.insurance.core.api.dto.agreement;

import lombok.*;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgreementDTO {

    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDTO> persons;

    private BigDecimal agreementPremium;
    private String uuid;
}
