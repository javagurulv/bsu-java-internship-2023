package lv.javaguru.travel.insurance.core.api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private String personFirstName;
    private String personLastName;
    private UUID personIc;
    private String medicalRiskLimitLevel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Minsk")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date personBirthDate;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal personPremium;

    @JsonAlias("selected_risks")
    @JsonProperty("selected_risks")
    private List<RiskDTO> selectedRisks;

}
