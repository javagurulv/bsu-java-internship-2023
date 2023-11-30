package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {
    //Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);
   /* @Query("SELECT cdr from CountryDefaultDayRate cdr " +
            "where cdr.country_ic = :country_ic ")*/
    Optional<CountryDefaultDayRate> findByCountryIc(String countryIc);
            //@Param("country_ic") String country_ic
    //);
}
