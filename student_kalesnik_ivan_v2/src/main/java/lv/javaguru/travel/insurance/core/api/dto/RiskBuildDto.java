package lv.javaguru.travel.insurance.core.api.dto;

public class RiskBuildDto {
    RiskDto riskDto = new RiskDto();

    public RiskDto build() {
        return RiskDto.builder()
                .riskIc(riskDto.getRiskIc())
                .premium(riskDto.getPremium())
                .build();
    }
}
