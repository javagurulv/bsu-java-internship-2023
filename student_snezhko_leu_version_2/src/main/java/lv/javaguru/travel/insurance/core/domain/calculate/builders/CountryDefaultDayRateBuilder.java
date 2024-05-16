package lv.javaguru.travel.insurance.core.domain.calculate.builders;

import lv.javaguru.travel.insurance.core.domain.calculate.medical.CountryDefaultDayRate;

import java.math.BigDecimal;

public class CountryDefaultDayRateBuilder {
    private Long id;
    private String countryIc;
    private BigDecimal coefficient;

    public static CountryDefaultDayRateBuilder createCountryDefaultDayRate() {
        return new CountryDefaultDayRateBuilder();
    }

    public CountryDefaultDayRate build() {
        return new CountryDefaultDayRate(id, countryIc, coefficient);
    }

    public CountryDefaultDayRateBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CountryDefaultDayRateBuilder withCountryIc(String ic) {
        this.countryIc = ic;
        return this;
    }

    public CountryDefaultDayRateBuilder withCoefficient(BigDecimal coeff) {
        this.coefficient = coeff;
        return this;
    }
}
