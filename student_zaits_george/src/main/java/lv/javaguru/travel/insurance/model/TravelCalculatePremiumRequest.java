package lv.javaguru.travel.insurance.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
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
