package lv.javaguru.travel.insurance.core.api.command.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelGetAgreementCoreCommand {
    private String uuid;
}
