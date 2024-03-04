package lv.javaguru.travel.insurance.core.api.dto;

import java.math.BigDecimal;

public class RiskDTOBuilder {
    private String riskIc;
    private BigDecimal riskPremium;

    public RiskDTOBuilder createRiskDTO() {
        return new RiskDTOBuilder();
    }

    public RiskDTO build() {
        return new RiskDTO(riskIc, riskPremium);
    }

    public RiskDTOBuilder withRiskIc(String ic) {
        this.riskIc = ic;
        return this;
    }

    public RiskDTOBuilder withRiskPremium(BigDecimal premium) {
        this.riskPremium = premium;
        return this;
    }
}
