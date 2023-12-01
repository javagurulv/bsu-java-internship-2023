package lv.javaguru.travel.insurance.dto.internal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.CoreResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import lv.javaguru.travel.insurance.dto.v2.PersonResponseDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelGetAgreementResponse extends CoreResponse {

    private String uuid;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date agreementDateTo;

    private String country;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    @JsonAlias("persons")
    private List<PersonResponseDTO> persons;

    public TravelGetAgreementResponse(List<ValidationError> errors) {
        super(errors);
    }

}
