package legacyfighter.dietary;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxConfigRepository extends JpaRepository<TaxConfig, Long> {
    TaxConfig findByCountryCode(CountryCode countryCode);

    @EntityGraph(attributePaths = {"taxRules"})
    List<TaxConfig> getAllBy();
}
