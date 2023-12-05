package lv.javaguru.travel.insurance.core.api.dto.builders;

import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;

import java.math.BigDecimal;

public class RiskDTOBuilder {

    private String riskIc;

    private BigDecimal premium;

    public static RiskDTOBuilder createRiskDTO() {
        return new RiskDTOBuilder();
    }

    public RiskDTO build() {
        RiskDTO riskDTO = new RiskDTO();
        riskDTO.setRiskIc(riskIc);
        riskDTO.setPremium(premium);
        return riskDTO;
    }

    public RiskDTOBuilder withRiskIc(String riskIc) {
        this.riskIc = riskIc;
        return this;
    }

    public RiskDTOBuilder withPremium(BigDecimal premium) {
        this.premium = premium;
        return this;
    }

}
