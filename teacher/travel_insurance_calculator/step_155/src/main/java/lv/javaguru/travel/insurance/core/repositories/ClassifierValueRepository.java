package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassifierValueRepository extends JpaRepository<ClassifierValue, Long> {

    @Cacheable(cacheNames = {"classifierValueTitleAndIcCache"}, key = "#p0 + #p1", unless="#result == null")
    @Query("SELECT cv from ClassifierValue cv " +
            "left join cv.classifier c " +
            "where c.title = :classifierTitle " +
            "and cv.ic = :ic")
    Optional<ClassifierValue> findByClassifierTitleAndIc(
            @Param("classifierTitle") String classifierTitle,
            @Param("ic") String ic
    );

}
