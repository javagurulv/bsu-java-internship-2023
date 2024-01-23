package lv.javaguru.travel.insurance.core.api.command.calculate.premium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumCoreCommand {
    private AgreementDTO agreement;
}
