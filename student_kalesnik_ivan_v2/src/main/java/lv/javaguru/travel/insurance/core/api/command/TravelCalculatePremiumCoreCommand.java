package lv.javaguru.travel.insurance.core.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@
public class TravelCalculatePremiumCoreCommand {
    private AgreementDto agreement;
}
