package lv.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @JsonAlias("person_first_name")
    private String personFirstName;
    @JsonAlias("person_last_name")
    private String personLastName;
    @JsonAlias("personBirthDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @JsonAlias("personal_code")
    private String personalCode;
    @JsonAlias("medical_risk_limit_level")
    private String medicalRiskLimitLevel;
}
