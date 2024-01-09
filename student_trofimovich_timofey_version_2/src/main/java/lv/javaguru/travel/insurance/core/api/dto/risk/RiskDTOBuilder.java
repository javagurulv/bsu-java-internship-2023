package lv.javaguru.travel.insurance.core.api.dto.risk;

import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class RiskDTOBuilder {
    private String riskIc;
    private BigDecimal premium;

    public static RiskDTOBuilder createRisk() {
        return new RiskDTOBuilder();
    }
    public RiskDTOBuilder withRiskIc(String riskIc) {
        this.setRiskIc(riskIc);
        return this;
    }

    public RiskDTOBuilder withPremium(BigDecimal premium) {
        this.setPremium(premium);
        return this;
    }

    public RiskDTO build() {
        RiskDTO riskDTO = new RiskDTO();
        riskDTO.setPremium(premium);
        riskDTO.setRiskIc(riskIc);
        return riskDTO;
    }
}
