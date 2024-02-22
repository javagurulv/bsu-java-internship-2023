package lv.javaguru.travel.insurance.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumRequest {

    @NotNull
    @NotBlank
    private String personFirstName;

    @NotNull
    @NotBlank
    private String personLastName;

    @Past
    @NotNull
    private Date agreementDateFrom;

    @NotNull
    @FutureOrPresent
    private Date agreementDateTo;

}
