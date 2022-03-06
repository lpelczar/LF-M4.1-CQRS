package legacyfighter.dietary.taxconfigview;

import legacyfighter.dietary.TaxRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaxConfigViewCreator {

    private final EntityBasedTaxConfigViewCreator entityBasedTaxConfigViewCreator;

    public TaxConfigViewCreator(EntityBasedTaxConfigViewCreator entityBasedTaxConfigViewCreator) {
        this.entityBasedTaxConfigViewCreator = entityBasedTaxConfigViewCreator;
    }

    public Map<String, List<TaxRule>> create() {
        return entityBasedTaxConfigViewCreator.create();
    }
}
