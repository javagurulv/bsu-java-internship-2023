package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {

    /*
    @Query("SELECT country_default_day_rate FROM COUNTRY_DEFAULT_DAY_RATE AS cddr" +
            "WHERE country_ic = :country_ic")
    public Optional<CountryDefaultDayRate> defaultDayRateByCountry(@Param("country_ic") String countryIc);
     */

    @Query("SELECT cddr from CountryDefaultDayRate as cddr where country_ic = :ic")
    public Optional<CountryDefaultDayRate> findByCountryIc(@Param("ic")String countryIc);
}
