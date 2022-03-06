package legacyfighter.dietary.taxconfigview;

import legacyfighter.dietary.TaxRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static legacyfighter.dietary.config.FeatureFlags.EAGER_TAX_CONFIG_VIEW;

@Service
public class TaxConfigViewCreator {

    private final OldTaxConfigViewCreator oldTaxConfigViewCreator;
    private final FasterTaxConfigViewCreator fasterTaxConfigViewCreator;

    public TaxConfigViewCreator(OldTaxConfigViewCreator oldTaxConfigViewCreator,
                                FasterTaxConfigViewCreator fasterTaxConfigViewCreator) {
        this.oldTaxConfigViewCreator = oldTaxConfigViewCreator;
        this.fasterTaxConfigViewCreator = fasterTaxConfigViewCreator;
    }

    public Map<String, List<TaxRule>> create() {
        if (shouldUseFasterCreation()) {
            return fasterTaxConfigViewCreator.create();
        }
        return oldTaxConfigViewCreator.create();
    }

    private boolean shouldUseFasterCreation() {
        return EAGER_TAX_CONFIG_VIEW.isActive();
    }
}
