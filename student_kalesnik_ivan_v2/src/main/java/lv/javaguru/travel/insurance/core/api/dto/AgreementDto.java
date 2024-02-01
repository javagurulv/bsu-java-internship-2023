package lv.javaguru.travel.insurance.core.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class AgreementDto {
    private Date agreementDateFrom;

    private Date agreementDateTo;

    private String country;

    private List<String> selectedRisks;

    private List<PersonDto> persons;

    private BigDecimal agreementPremium;

    private String uuid;

}
