package lv.javaguru.travel.insurance.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AgeCoefficientBuilder {

    private Long id;
    private Integer ageFrom;
    private Integer ageTo;
    private BigDecimal coefficient;

    public static AgeCoefficientBuilder createAgeCoefficient() {
        return new AgeCoefficientBuilder();
    }

    public AgeCoefficient build() {
        return new AgeCoefficient(id, ageFrom, ageTo, coefficient);
    }

    public AgeCoefficientBuilder withId(Long id) {
        this.id = id;
        return this;
    }


    public AgeCoefficientBuilder withAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
        return this;
    }

    public AgeCoefficientBuilder withAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
        return this;
    }

    public AgeCoefficientBuilder withCoefficient(BigDecimal coeff) {
        this.coefficient = coeff;
        return this;
    }
}
