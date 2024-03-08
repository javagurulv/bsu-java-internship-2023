package lv.javaguru.travel.insurance.core.domain;

import java.math.BigDecimal;

public class MedicalRiskLimitLevelBuilder {
    private Long id;
    private String medicalRiskLimitLevelIc;
    private BigDecimal coefficient;

    public static MedicalRiskLimitLevelBuilder createMedicalRiskLimitLevel() {
        return new MedicalRiskLimitLevelBuilder();
    }

    public MedicalRiskLimitLevel build() {
        return new MedicalRiskLimitLevel(id, medicalRiskLimitLevelIc, coefficient);
    }

    public MedicalRiskLimitLevelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MedicalRiskLimitLevelBuilder withIc(String ic) {
        this.medicalRiskLimitLevelIc = ic;
        return this;
    }

    public MedicalRiskLimitLevelBuilder withCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
        return this;
    }
}
